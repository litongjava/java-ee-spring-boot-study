package com.litongjava.study.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author create by ping-e-lee on 2021年6月24日 下午11:27:59 
 * @version 1.0 
 * @desc
 */
@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    // SpringApplicationWrapper.run(Application.class, args, true);
    SpringApplication.run(Application.class, args);
  }
}
