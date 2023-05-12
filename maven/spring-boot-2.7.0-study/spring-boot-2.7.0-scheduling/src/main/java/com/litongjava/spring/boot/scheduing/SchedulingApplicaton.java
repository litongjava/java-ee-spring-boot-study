package com.litongjava.spring.boot.scheduing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class SchedulingApplicaton {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(SchedulingApplicaton.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "(ms)");
  }
}