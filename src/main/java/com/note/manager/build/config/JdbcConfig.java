package com.note.manager.build.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class JdbcConfig {
    @Bean
    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            System.getenv("PSQL_URL"),
            System.getenv("PSQL_USER"),
            System.getenv("PSQL_PASS")
        );
    }
}
