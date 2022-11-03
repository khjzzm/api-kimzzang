package kim.zzang.webservice.config.datasources;

import kim.zzang.webservice.config.annotation.ExcludeScan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@RequiredArgsConstructor
//@ExcludeScan
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    private final DataSourceKey dataSourceKey;

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        if (isReadOnly) {
            return dataSourceKey.getDefaultSlaveKey();
        } else {
            return dataSourceKey.getMasterKey();
        }
    }
}