package com.alit.study.h2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("hello")
@Slf4j
public class HelloController {
  @RequestMapping
  public String index() {
    log.info("hello");
    return "hello";
  }
}
