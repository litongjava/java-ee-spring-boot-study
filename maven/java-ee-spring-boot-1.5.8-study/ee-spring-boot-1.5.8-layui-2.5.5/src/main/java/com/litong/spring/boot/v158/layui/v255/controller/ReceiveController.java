package com.litong.spring.boot.v158.layui.v255.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litong.spring.boot.v158.layui.v255.dataobj.JsonBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bill robot
 * @date 2020年6月9日_上午10:10:34 
 * @version 1.0 
 * @desc
 */
@RestController
@RequestMapping("receive")
@Slf4j
public class ReceiveController {
  @RequestMapping("array")
  public JsonBean<Void> array(@RequestParam(value = "ids[]") int[] ids) {
    log.info("ids {}", ids);
    return new JsonBean<Void>();
  }
}
