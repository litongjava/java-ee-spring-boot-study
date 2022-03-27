package com.litongjava.spring.boot.controller;


import com.litongjava.spring.boot.model.SmzdmModel;
import com.litongjava.spring.boot.services.SmzdmService;
import com.litongjava.spring.boot.vo.JsonBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("smzdm")
public class SmzdmController {

  @Autowired
  private SmzdmService smzdmService;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public JsonBean<List<SmzdmModel>> list() {
    log.info("请求什么值得买的getAll接口");
    List<SmzdmModel> smzdmModels = smzdmService.selectAll();
    if (smzdmModels.size() > 0) {
      return new JsonBean<List<SmzdmModel>>(smzdmModels);
    } else {
      return null;
    }
  }
}