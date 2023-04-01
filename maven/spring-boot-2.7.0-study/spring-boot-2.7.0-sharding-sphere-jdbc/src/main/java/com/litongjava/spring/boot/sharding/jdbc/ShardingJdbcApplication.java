package com.litongjava.spring.boot.sharding.jdbc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

import top.ppnt.spring.boot.utils.startup.StartupUtils;

@SpringBootApplication
@MapperScan({"com.litongjava.spring.boot.sharding.jdbc.user"})
public class ShardingJdbcApplication{

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ConfigurableApplicationContext ctx = SpringApplicationWrapper.run(ShardingJdbcApplication.class, args);
    StartupUtils.info(start, ctx);
  }
}