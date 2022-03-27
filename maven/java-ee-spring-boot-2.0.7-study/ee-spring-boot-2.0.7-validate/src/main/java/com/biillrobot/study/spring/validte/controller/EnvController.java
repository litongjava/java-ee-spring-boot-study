package com.biillrobot.study.spring.validte.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("env")
public class EnvController {
  private List<String> keys = new ArrayList<>();
  @Autowired
  public Environment env;

  public EnvController() {
    keys.add("spring.mvc.view.prefix");
    keys.add("spring.mvc.view.suffix");
  }

  @RequestMapping("info")
  public Map<String, String> info() {
    Map<String, String> retval = new HashMap<>();
    for (String k : keys) {
      retval.put(k, env.getProperty(k));
    }
    return retval;
  }
}
