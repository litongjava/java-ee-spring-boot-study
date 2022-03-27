package com.litongjava.module.spring.boot.ztree.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by ping-e-lee on 2021年4月11日 下午6:26:33 
 * @version 1.0 
 * @desc
 */
@RestController
@RequestMapping("hello")
public class IndexController {
  
  @RequestMapping
  public String hello() {
    return "hello";
  }

}
