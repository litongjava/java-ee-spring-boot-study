package com.litong.spring.boot.mybatis.tk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.litong.spring.boot.mybatis.tk.mapper")
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
