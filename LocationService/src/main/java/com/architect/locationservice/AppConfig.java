package com.architect.locationservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        // Define your datasource here to communicate with database
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/Location Service");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
        return dataSource;
    }

    @Bean
    public Connection connection() throws SQLException {
        return dataSource().getConnection();
    }

    @Bean
    public UserDAO userDAO() throws SQLException {
        return new UserDAO(connection());
    }

    @Bean
    public LocationDAO locationDAO() throws SQLException {
        return new LocationDAO(connection());
    }

    @Bean
    public VisitedLocationDAO visitedLocationDAO() throws SQLException {
        return new VisitedLocationDAO(connection());
    }

}
