package com.litong.spring.boot.v222.secruty.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * 验证码的过滤器
 */
@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

  MyAuthenticationFailureHandler myAuthenticationFailureHandler;

  public MyAuthenticationFailureHandler getMyAuthenticationFailureHandler() {
    return myAuthenticationFailureHandler;
  }

  public void setMyAuthenticationFailureHandler(MyAuthenticationFailureHandler myAuthenticationFailureHandler) {
    this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
  }

  /**
   * 操作session的工具类
   */
  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    // 只有当处理登录功能时才执行
    if (StringUtils.equals("/auth/form", request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
      // 最好做try catch 处理异常
      try {
        validate(new ServletWebRequest(request));
      } catch (AuthenticationException e) {
        myAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
        return;
      }
    }
    filterChain.doFilter(request, response);
  }

  // 执行验证码逻辑
  private void validate(ServletWebRequest request) throws AuthenticationException, ServletRequestBindingException {
    // 取出session中的验证码对象
    ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
    // 如果为空
    if (codeInSession == null) {
      throw new ValidateCodeException("code in session is null");
    }
    // 如果过期
    if (codeInSession.isExpried()) {
      sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
      throw new ValidateCodeException("code has expreied");
    }

    // 取出表单提交中的验证码信息
    String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

    if (StringUtils.isBlank(codeInRequest)) {
      throw new ValidateCodeException("code in request is null");
    }

    if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
      throw new ValidateCodeException("code is not right");
    }
    log.info("验证码正确");
    // 验证通过，清除session中的验证码信息
    sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
    
  }
}