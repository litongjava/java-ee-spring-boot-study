package com.litongjava.spring.boot.sutdy.map.struct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litongjava.spring.boot.sutdy.map.struct.convert.UserStructConvert;
import com.litongjava.spring.boot.sutdy.map.struct.dto.UserDo;
import com.litongjava.spring.boot.sutdy.map.struct.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserStructConvert userStructConvert;

  @GetMapping("/userDto")
  public String convert() {
    UserDo src = new UserDo("Ping", "123456", "http://litongjava.com");
    log.info("userDo:{}", src);
    UserVo userVo = userStructConvert.toUserVo(src);
    log.info("userVo:{}", userVo);
    UserDo dst = userStructConvert.toUserDo(userVo);
    log.info("userDo:{}", dst);
    return "OK";
  }
}