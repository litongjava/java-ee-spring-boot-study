package com.litongjava.spring.boot.v241.controller;

import com.ejlchina.searcher.BeanSearcher;
import com.litongjava.spring.boot.v241.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@RequestMapping("/user")
public class UserControllerMapParameter {


  @Autowired
  private BeanSearcher beanSearcher;

  @GetMapping("/bs")
  public List<User> bs(@RequestParam Map<String, Object> params) {
    // 你是否对入参 Map 有偏见？如果有，请耐心往下看，有方案
    return beanSearcher.searchList(User.class, params);
  }
}
