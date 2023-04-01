package com.biillrobot.study.spring.validte.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("deprecation")
@RestController
@RequestMapping("validate")
@Slf4j
@Validated
public class ValidateController {
  @RequestMapping("validate")
  public String validateTest(@NotBlank(message = "地址不能为空！") String address) {
    log.info("address={}", address);
    return "success";
  }
}
