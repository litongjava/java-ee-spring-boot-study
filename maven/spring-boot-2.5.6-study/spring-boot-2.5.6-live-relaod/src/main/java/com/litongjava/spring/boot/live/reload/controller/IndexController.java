package com.litongjava.spring.boot.live.reload.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("")
public class IndexController {
  @RequestMapping("")
  public String index() {
    return "index";
  }
}