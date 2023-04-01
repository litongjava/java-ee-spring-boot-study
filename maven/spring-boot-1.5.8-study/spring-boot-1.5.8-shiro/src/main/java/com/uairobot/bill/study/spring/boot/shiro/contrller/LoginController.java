package com.uairobot.bill.study.spring.boot.shiro.contrller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uairobot.bill.study.spring.boot.shiro.bean.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {

  @RequestMapping("/login")
  public String login(User user) {
    // 添加用户认证信息
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
    log.info("usernamePasswordToken:{}", usernamePasswordToken);
    try {
      // 进行验证，这里可以捕获异常，然后返回对应信息
      subject.login(usernamePasswordToken);
      subject.checkRole("admin");
      subject.checkPermissions("query", "add");
    } catch (AuthenticationException e) {
      e.printStackTrace();
      return "账号或密码错误！";
    } catch (AuthorizationException e) {
      e.printStackTrace();
      return "没有权限";
    }
    return "login success";
  }

  // 注解验角色和权限
  @RequiresRoles("admin")
  @RequiresPermissions("add")
  @RequestMapping("/index")
  public String index() {
    return "index!";
  }

  /**
   * 登出 
   * @param session
   * @param model
   * @return
   */
  @RequestMapping("/logout")
  public String logout(HttpSession session, Model model) {
    log.info("logout");
    Subject subject = SecurityUtils.getSubject();
    subject.logout();
    model.addAttribute("msg", "安全退出！");
    return "login";
  }

}