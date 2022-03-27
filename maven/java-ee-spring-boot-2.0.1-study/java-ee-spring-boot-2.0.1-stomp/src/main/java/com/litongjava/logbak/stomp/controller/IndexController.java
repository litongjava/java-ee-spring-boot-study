package com.litongjava.logbak.stomp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class IndexController {

  @RequestMapping("")
  public String indexEmpyt() {
    log.info("this is index");
    return "index";
  }
  
  @RequestMapping("exception")
  public String exception() {
    
    throw new RuntimeException("运行异常");
  }
}
