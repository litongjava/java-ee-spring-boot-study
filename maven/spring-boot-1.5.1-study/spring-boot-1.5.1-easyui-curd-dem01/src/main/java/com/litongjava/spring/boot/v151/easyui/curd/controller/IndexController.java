package com.litongjava.spring.boot.v151.easyui.curd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ping E Lee on 2022-08-20_20:09
 */
@RestController
@RequestMapping("")
public class IndexController {
  @RequestMapping("")
  public String index() {
    return "this is index";
  }
}