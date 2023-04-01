package com.litongjava.spring.boot.kotlin.hello.controller

/**
 * Created by litonglinux@qq.com on 9/4/2021_10:36 PM
 */

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

  @GetMapping("/")
  fun index(): String {
    return "hello kotlin"
  }
}