package com.litongjava.spring.boot.mybatis.plus.h2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.litongjava.spring.boot.mybatis.plus.h2.dao")
public class App {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(App.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end-start)+"ms");
  }
}