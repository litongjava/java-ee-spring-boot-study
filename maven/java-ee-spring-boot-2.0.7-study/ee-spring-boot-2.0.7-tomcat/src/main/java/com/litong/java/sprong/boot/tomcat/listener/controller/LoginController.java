package com.litong.java.sprong.boot.tomcat.listener.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litong
 * @date 2020年9月14日_上午10:55:12 
 * @version 1.0 
 * @desc
 */
@RestController
@RequestMapping("user")
public class LoginController {
  @RequestMapping("login")
  public String login() {
    
    return null;
  }
}
