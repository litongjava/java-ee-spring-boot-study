package com.litongjava.spring.boot.hello;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

/**
 * @author create by ping-e-lee on 2021年6月25日 下午2:43:47 
 * @version 1.0 
 * @desc
 */
@SpringBootApplication
public class HelloApplication {
  public static void main(String[] args) {
    //SpringApplication.run(Application.class, args);
    //0.369 second
    //0.361 second
    SpringApplicationWrapper.run(HelloApplication.class, args, true,true);
  }
}
