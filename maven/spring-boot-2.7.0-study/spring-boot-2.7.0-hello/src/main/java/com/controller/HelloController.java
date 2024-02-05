package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

  @Autowired
  private ApplicationContext context;

  // private Environment environment;
  @RequestMapping
  public String index() {
    return "Hello 1";
  }

  @RequestMapping("/request-and-response")
  public String requestAndResponse(HttpServletRequest request, HttpServletResponse response) {
    return request.toString() + " and " + response.toString();
  }

  @RequestMapping("/beans")
  public String[] beans() {
    return context.getBeanDefinitionNames();

  }

}
