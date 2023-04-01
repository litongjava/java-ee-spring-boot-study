package com.litongjava.spring.boot.hello;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

/**
 * @author create by ping-e-lee on 2021年6月25日 下午2:37:52 
 * @version 1.0 
 * @desc
 */
@SpringBootApplication
public class Application {
  public static void main(String[] args) {
//    SpringApplication.run(Application.class, args);
    SpringApplicationWrapper.run(Application.class, args, true);
  }
}
