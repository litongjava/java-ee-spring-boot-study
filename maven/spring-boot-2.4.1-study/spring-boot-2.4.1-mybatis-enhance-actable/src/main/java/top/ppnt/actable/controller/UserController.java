package top.ppnt.actable.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import top.ppnt.actable.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ping E Lee
 * @since 2022-05-06
 */
@Controller
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;
  
  public void list() {
    userService.count();
  }
}

