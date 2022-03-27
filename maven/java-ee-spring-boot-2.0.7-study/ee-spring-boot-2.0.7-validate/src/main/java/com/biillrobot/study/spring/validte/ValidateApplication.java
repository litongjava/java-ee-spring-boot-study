package com.biillrobot.study.spring.validte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ValidateApplication {
  public static void main(String[] args) {
    SpringApplication.run(ValidateApplication.class, args);
    log.info("启动完成");
  }
}