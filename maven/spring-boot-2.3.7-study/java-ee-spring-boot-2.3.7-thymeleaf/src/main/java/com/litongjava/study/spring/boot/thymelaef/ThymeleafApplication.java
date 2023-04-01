package com.litongjava.study.spring.boot.thymelaef;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author create by Ping E Lee on 2022年3月12日 上午11:35:22 
 *
 */
@SpringBootApplication
public class ThymeleafApplication {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(ThymeleafApplication.class, args);
    long end = System.currentTimeMillis();
    System.out.println("启动时间:" + (end - start) + "ms");
  }
}
