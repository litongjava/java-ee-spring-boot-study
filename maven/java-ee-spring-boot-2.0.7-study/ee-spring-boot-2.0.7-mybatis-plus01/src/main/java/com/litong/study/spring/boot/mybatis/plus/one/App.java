package com.litong.study.spring.boot.mybatis.plus.one;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@MapperScan("com.litong.study.spring.boot.mybatis.plus.one.mapper")
@Slf4j
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
    log.info("========================启动完毕========================");
  }
}
