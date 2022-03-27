package com.litong.study.spring.boot.mybatis.plus.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.litong.study.spring.boot.mybatis.plus.one.entity.UserNormal;
import com.litong.study.spring.boot.mybatis.plus.one.service.IUserNormalService;

/**
 * <p>
 * 普通用户 前端控制器
 * </p>
 *
 * @author litong
 * @since 2020-04-24
 */
@RestController
@RequestMapping("/userNormal")
public class UserNormalController {
  @Autowired
  private IUserNormalService userNormalService;

  @GetMapping(value = "/list")
  public JSONObject list() {
    Page<UserNormal> page = userNormalService.selectPage(new Page<>(1, 10));
    JSONObject result = new JSONObject();
    result.put("users", page);
    return result;
  }
}
