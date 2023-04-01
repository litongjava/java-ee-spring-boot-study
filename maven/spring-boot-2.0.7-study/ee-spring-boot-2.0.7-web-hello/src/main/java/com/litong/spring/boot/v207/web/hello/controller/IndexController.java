package com.litong.spring.boot.v207.web.hello.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class IndexController {

  /**
   * 测试不同的域名访问
   * 
   * @param request 
   * @return
   */
  @RequestMapping
  public String index(HttpServletRequest request) {
    String serverName = request.getServerName();
    int serverPort = request.getServerPort();
    return serverName + ":" + serverPort;
  }

  @RequestMapping("hello")
  public String hello() {
    return "httlloxx";
  }
}
