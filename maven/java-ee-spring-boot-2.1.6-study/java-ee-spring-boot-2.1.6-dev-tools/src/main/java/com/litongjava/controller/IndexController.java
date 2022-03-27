package com.litongjava.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by ping-e-lee on 2021年7月14日 下午10:42:24 
 * @version 1.0 
 * @desc
 */
@RestController
@RequestMapping
public class IndexController {
  @RequestMapping()
  public String index() {
    return "indexx ";
  }
  @RequestMapping("classLoader")
  public String classLoader() {
    return this.getClass().getClassLoader().toString();
  }
}
