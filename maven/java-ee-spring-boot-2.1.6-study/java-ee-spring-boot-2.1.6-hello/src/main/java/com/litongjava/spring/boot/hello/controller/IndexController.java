package com.litongjava.spring.boot.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by ping-e-lee on 2021年7月10日 下午11:14:07 
 * @version 1.0 
 * @desc
 */
@RequestMapping("")
@RestController
public class IndexController {
  @RequestMapping
  public String index(){
    return "index";
  }

  @RequestMapping("version")
  public String version() {
    return "v1.0-2021年7月10日23:28:41";
  }
}
