package com.litong.java.spring.boot.thread.pool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author litong
 * @date 2020年9月15日_上午8:47:00 
 * @version 1.0 
 * @desc
 */
@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(Application.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }

}
