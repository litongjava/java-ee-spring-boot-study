package com.litong.study.spring.boot.jenkins;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by litongjava@qq.com on 2020/5/4_12:27
 */
@RestController
@RequestMapping("hello")
public class HelloController {

  @RequestMapping()
  public String hello(){
    return "hello";
  }
}
