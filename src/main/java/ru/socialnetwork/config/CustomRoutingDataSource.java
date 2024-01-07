package ru.socialnetwork.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class CustomRoutingDataSource extends AbstractRoutingDataSource {
//    @Override
//    protected Object determineCurrentLookupKey() {
//        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "replica" : "master";
//    }

    @Override
    protected Object determineCurrentLookupKey() {
        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            return Math.random() < 0.5 ? "replica1" : "replica2";
        } else {
            return "master";
        }
    }
}
