package ru.socialnetwork.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource masterDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("pass");

        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(5);
        dataSource.setIdleTimeout(30000);
        dataSource.setPoolName("MasterHikariCP");

        return dataSource;
    }

    @Bean
    public DataSource replicaDataSource1() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:15432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("pass");

        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(5);
        dataSource.setIdleTimeout(30000);
        dataSource.setPoolName("ReplicaHikariCP1");

        return dataSource;
    }

    @Bean
    public DataSource replicaDataSource2() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:25432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("pass");

        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(5);
        dataSource.setIdleTimeout(30000);
        dataSource.setPoolName("ReplicaHikariCP2");

        return dataSource;
    }

    @Bean
    @Primary
    public DataSource routingDataSource() {
        CustomRoutingDataSource customRoutingDataSource = new CustomRoutingDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", masterDataSource());
        targetDataSources.put("replica1", replicaDataSource1());
        targetDataSources.put("replica2", replicaDataSource2());

        customRoutingDataSource.setTargetDataSources(targetDataSources);
        customRoutingDataSource.setDefaultTargetDataSource(masterDataSource());

        return customRoutingDataSource;
    }
}
