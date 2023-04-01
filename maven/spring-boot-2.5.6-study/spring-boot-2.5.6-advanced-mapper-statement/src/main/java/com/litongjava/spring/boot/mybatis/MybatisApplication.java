package com.litongjava.spring.boot.mybatis;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

import top.ppnt.spring.boot.utils.startup.StartupUtils;

@SpringBootApplication
@MapperScan("com.litongjava.spring.boot.mybatis.mapper")
public class MybatisApplication{

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ConfigurableApplicationContext ctx = SpringApplicationWrapper.run(MybatisApplication.class, args);
    StartupUtils.info(start, ctx);
  }
}