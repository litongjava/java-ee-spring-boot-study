package com.litongjava.spring.boot.v241.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ping E Lee
 *
 */
@RestController
@RequestMapping("/")
public class IndexController {
  @RequestMapping("")
  public String index() {
    return "index";
  }
}
