package com.litong.java.spring.boot.thin.layout.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litong
 * @date 2020年9月17日_上午11:07:44 
 * @version 1.0 
 * @desc
 */
@RestController
@RequestMapping("hello")
public class HelloController {
  @RequestMapping
  public String hello() {
    return "hello";
  }

}
