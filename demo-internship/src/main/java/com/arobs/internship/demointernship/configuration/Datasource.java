package com.arobs.internship.demointernship.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class Datasource {



    @Bean(name="customDataSource")
    public DataSource customDataSource() {
        HikariConfig config = new HikariConfig();
        HikariDataSource ds;
        config.setJdbcUrl("jdbc:mysql://localhost:3306/meetup");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername("root");
        config.setPassword("pass");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
        return ds;
    }
/*
    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/meetup");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername("root");
        config.setPassword("pass");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }


    @Bean
    public Connection getConnection() throws SQLException {
        return dataSource().getConnection();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }*/

/*
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariDataSource getDataSource() {
        return (HikariDataSource) DataSourceBuilder.create().build();
    }*/

   /* @Bean
    public Connection getConn() throws SQLException {
        HikariConfig config = new HikariConfig("hikari.properties");
        HikariDataSource ds = new HikariDataSource(config);
        return ds.getConnection();
    }*/
}
