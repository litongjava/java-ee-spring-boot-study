package com.litong.study.spring.boot2_0_3.ajax.download.file.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController()
@RequestMapping("version")
public class VersionController {

  @RequestMapping()
  public JSONObject version() {
    JSONObject retval = new JSONObject();
    retval.put("vesion", "1.0");
    return retval;
  }
}
