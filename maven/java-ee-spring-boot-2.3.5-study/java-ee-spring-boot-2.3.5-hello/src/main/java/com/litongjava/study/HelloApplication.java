package com.litongjava.study;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

/**
 * @author create by ping-e-lee on 2021年7月13日 上午2:12:52 
 * @version 1.0 
 * @desc
 */
@SpringBootApplication
public class HelloApplication {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplicationWrapper.run(HelloApplication.class, args, true);
    long end = System.currentTimeMillis();
    System.out.println((end-start)+"ms");
  }
}
