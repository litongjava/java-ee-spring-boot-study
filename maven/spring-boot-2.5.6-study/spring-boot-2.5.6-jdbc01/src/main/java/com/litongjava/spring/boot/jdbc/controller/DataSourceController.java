package com.litongjava.spring.boot.jdbc.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataSourceController {
  @Resource
  private DataSource dataSource;

  @RequestMapping("/dataSource")
  public String dataSource() {
    System.out.println(dataSource);
    return dataSource.toString();
  }

  @RequestMapping("getConnection")
  public String getConnection() throws SQLException {
    String retval = null;
    // 查看默认的数据源 ：class com.zaxxer.hikari.HikariDataSource
    System.out.println(dataSource.getClass());
    // 获得数据库连接
    Connection connection = dataSource.getConnection();
    // 查看获得的连接 HikariProxyConnection@354350463 wrapping
    // com.mysql.cj.jdbc.ConnectionImpl@10895b16
    retval = connection.toString();
    System.out.println(retval);
    // 是 JDBC 的连接！
    System.out.println(connection);
    connection.close();
    return retval;
  }
}
