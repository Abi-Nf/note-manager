package com.note.manager.build.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.note.manager.build")
public class JdbcConfig {
    @Bean
    public DataSource ConnectionToDb(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("");
        dataSource.setUsername("");
        dataSource.setPassword("");
        return  dataSource;
    }
}
