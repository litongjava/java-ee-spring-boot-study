package com.litongjava.spring.boot.jfinal.active.record.controller;
/**
 * @author litongjava <litongjava@qq.com>
 *
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jfinal.plugin.activerecord.Record;
import com.litongjava.spring.boot.jfinal.active.record.model.User;
import com.litongjava.spring.boot.jfinal.active.record.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping("list")
  public List<Record> list() {
    return userService.list();
  }
  
  @RequestMapping("listForMap")
  public List<Map<String,Object>> listForMap() {
    return userService.listForMap();
  }
  @RequestMapping("listForMap2")
  public List<Map<String,Object>> listForMap2() {
    return userService.listForMap2();
  }
  
  @RequestMapping("listForModel")
  public List<User> listForModel() {
    return userService.listForModel();
  }
  
  @RequestMapping("listFromTemplate")
  public List<User> listFromTemplate(){
    return userService.listFromTemplate();
  }
}
