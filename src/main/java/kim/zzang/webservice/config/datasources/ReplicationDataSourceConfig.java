package kim.zzang.webservice.config.datasources;

import com.zaxxer.hikari.HikariDataSource;
import kim.zzang.webservice.config.annotation.ExcludeScan;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@MapperScan(
        basePackages = {"kim/zzang/webservice/api/*/*/repository"}
)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ExcludeScan
public class ReplicationDataSourceConfig {
    private final DataSourceKey dataSourceKey;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public DataSource routingDataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource(dataSourceKey);

        Map<Object, Object> sources = new HashMap<Object, Object>() {
            {
                put(dataSourceKey.getMasterKey(), masterDataSource);
                put(dataSourceKey.getDefaultSlaveKey(), slaveDataSource);
            }
        };

        routingDataSource.setTargetDataSources(sources);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(routingDataSource(masterDataSource(), slaveDataSource()));
    }
}