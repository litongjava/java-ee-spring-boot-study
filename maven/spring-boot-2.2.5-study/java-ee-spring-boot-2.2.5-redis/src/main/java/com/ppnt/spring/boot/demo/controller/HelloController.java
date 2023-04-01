package com.ppnt.spring.boot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ppnt.spring.boot.demo.dto.HelloRespDTO;
import com.ppnt.spring.boot.demo.service.HelloService;

@RestController
public class HelloController {

  @Autowired
  HelloService helloService;

  @GetMapping("/hello")
  public HelloRespDTO hello() {
    String id = "1";
    HelloRespDTO helloRespDTO = helloService.helloCache(id);
    return helloRespDTO;
  }

  @GetMapping("/hello2")
  public HelloRespDTO helloClear() {
    String id = "1";
    HelloRespDTO helloRespDTO = helloService.helloClear(id);
    return helloRespDTO;
  }
}