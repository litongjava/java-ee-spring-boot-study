package com.litong.spring.boot.v158.web;

import java.io.Serializable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litong.spring.boot.v158.mp.vo.JsonBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bill robot
 * @date 2020年6月16日_下午5:31:16 
 * @version 1.0 
 * @desc
 */
@RestController()
@RequestMapping("data")
@Slf4j
public class DataController {

  @RequestMapping("index")
  public JsonBean<String> index() {
    JsonBean<String> jsonBean = new JsonBean<>();
    jsonBean.setData("hello");
    return jsonBean;
  }
  @RequestMapping("removeById")
  public JsonBean<Boolean> removeById(Serializable id) {
    log.info("id {}",id);
    return new JsonBean<Boolean>();
  }
}
