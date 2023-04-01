package com.uairobot.bill.study.mybatis.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.uairobot.bill.study.mybatis.plus.mapper")
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
