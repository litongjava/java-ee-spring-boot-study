package com.litongjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author create by ping-e-lee on 2021年7月14日 下午10:41:05 
 * @version 1.0 
 * @desc
 */
@SpringBootApplication
//@SpringBootApplication(exclude = LocalDevToolsAutoConfiguration.class)
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
