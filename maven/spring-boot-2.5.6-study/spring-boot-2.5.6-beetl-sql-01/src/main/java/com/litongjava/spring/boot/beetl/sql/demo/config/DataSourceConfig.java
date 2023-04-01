package com.litongjava.spring.boot.beetl.sql.demo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

  @Bean(name = "datasource")
  public DataSource datasourceForBeetl(Environment env) {
    HikariDataSource hds = new HikariDataSource();
    hds.setJdbcUrl(env.getProperty("spring.datasource.url"));
    hds.setUsername(env.getProperty("spring.datasource.username"));
    hds.setPassword(env.getProperty("spring.datasource.password"));
    hds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));

    return hds;
  }
}