package com.litongjava.spring.boot.v216.api.restart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ExampleRestartApplication {

  public static String[] args;
  public static ConfigurableApplicationContext context;

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ExampleRestartApplication.args = args;
    context = SpringApplication.run(ExampleRestartApplication.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}