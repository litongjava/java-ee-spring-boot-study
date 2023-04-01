package com.litongjava.spring.boot.v151.easyui.curd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Ping E Lee on 2022-08-20_20:08
 */
@SpringBootApplication
@EnableJpaRepositories
public class CurdApp {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(CurdApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
