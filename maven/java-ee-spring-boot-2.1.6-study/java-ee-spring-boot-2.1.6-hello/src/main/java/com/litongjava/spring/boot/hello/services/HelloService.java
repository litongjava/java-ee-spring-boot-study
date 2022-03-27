package com.litongjava.spring.boot.hello.services;

import org.springframework.stereotype.Service;

/**
 * @author create by ping-e-lee on 2021年7月13日 上午9:50:04 
 * @version 1.0 
 * @desc
 */
@Service
public class HelloService {

  public String hello() {
    return "hello";
  }

}
