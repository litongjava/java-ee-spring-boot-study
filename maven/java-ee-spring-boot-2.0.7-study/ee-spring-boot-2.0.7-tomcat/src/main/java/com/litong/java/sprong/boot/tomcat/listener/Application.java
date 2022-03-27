package com.litong.java.sprong.boot.tomcat.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author litong
 * @date 2020年9月14日_上午10:54:08 
 * @version 1.0 
 * @desc
 */
@SpringBootApplication
@ServletComponentScan
public class Application {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(Application.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
