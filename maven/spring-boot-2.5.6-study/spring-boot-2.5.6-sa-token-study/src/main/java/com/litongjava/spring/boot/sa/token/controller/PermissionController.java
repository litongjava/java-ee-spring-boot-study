package com.litongjava.spring.boot.sa.token.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.stp.StpUtil;

@RestController
@RequestMapping("/permission")
public class PermissionController {

  public List<String> getPermissionList() {
    // 获取：当前账号所拥有的权限集合
    return StpUtil.getPermissionList();
  }

  public boolean hasPermission() {
    // 判断：当前账号是否含有指定权限, 返回 true 或 false
    return StpUtil.hasPermission("user.add");
  }

  public boolean checkPermission() {
    // 校验：当前账号是否含有指定权限, 如果验证未通过，则抛出异常: NotPermissionException
    try {
      StpUtil.checkPermission("user.add");
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean checkPermissionAnd() {
    try {
      // 校验：当前账号是否含有指定权限 [指定多个，必须全部验证通过]
      StpUtil.checkPermissionAnd("user.add", "user.delete", "user.get");
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean checkPermissionOr() {
    // 校验：当前账号是否含有指定权限 [指定多个，只要其一验证通过即可]
    try {
      StpUtil.checkPermissionOr("user.add", "user.delete", "user.get");
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
