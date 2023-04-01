package com.litongjava.study.spring.boot.thymelaef.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {

  @RequestMapping("hello-world")
  public String helloHtml(Map<String, Object> map) {
    map.put("hello", "i am litong");
    map.put("html", "<html> this is html</html>");
    return "/HelloWorld";
  }
}