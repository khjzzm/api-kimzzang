package kim.zzang.webservice;

import kim.zzang.webservice.config.annotation.ExcludeScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableCaching
@EnableBatchProcessing
@SpringBootApplication
@ComponentScan(
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {ExcludeScan.class})
        }
)
public class Application {

//    public static final String APPLICATION_LOCATIONS = "spring.config.location="
//            + "classpath:application.yml,"
//            + "/app/config/kimzzang/real-application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
//                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }
}

