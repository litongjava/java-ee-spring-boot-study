package com.litongjava.spring.boot.sa.token.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;

@RestController
@RequestMapping("user")
public class LoginController {

  @GetMapping("doLogin")
  public SaResult doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
    // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
    if ("zhang".equals(username) && "123456".equals(password)) {
      // StpUtil.login(id) 方法利用了 Cookie 自动注入的特性，自动向前端返回token信息
      StpUtil.login(10001);
      return SaResult.ok("登陆成功");
    }
    return SaResult.error("登陆失败");
  }

  @GetMapping("logout")
  public SaResult logout() {
    StpUtil.logout();
    return SaResult.ok("ok");
  }
}
