package com.uairobot.bill.study.spring.boot.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShiroLogoutFilter extends LogoutFilter {

  /**
   * 自定义登出,登出之后,清理当前用户redis部分缓存信息
   */
  @Override
  protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

    // 登出操作 清除缓存 subject.logout() 可以自动清理缓存信息,
    Subject subject = getSubject(request, response);
    log.info("subject:{}", subject);
    // 获取用识别信息
    PrincipalCollection principals = subject.getPrincipals();
    log.info("principals:{}", principals);
    // 清除redis中的数据
    // 略

    // 登出
    subject.logout();

    // 获取登出后重定向到的地址
    String redirectUrl = getRedirectUrl(request, response, subject);
    // 重定向
    issueRedirect(request, response, redirectUrl);
    return false;
  }
}