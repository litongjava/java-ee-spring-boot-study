package com.litongjava.spring.boot.beetl.sql.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    //SpringApplication.run(Application.class, args);
    SpringApplicationWrapper.run(Application.class, args);
  }
}