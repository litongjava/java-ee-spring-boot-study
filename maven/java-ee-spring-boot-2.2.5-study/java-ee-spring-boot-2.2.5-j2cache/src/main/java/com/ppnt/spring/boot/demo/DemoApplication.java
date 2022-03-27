package com.ppnt.spring.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(DemoApplication.class, args);
    long end = System.currentTimeMillis();
    System.out.println("共使用了" + (end - start) + "ms");
  }
}