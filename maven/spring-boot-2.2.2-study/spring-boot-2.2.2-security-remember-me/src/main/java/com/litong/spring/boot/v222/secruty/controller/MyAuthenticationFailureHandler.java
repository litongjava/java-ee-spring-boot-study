package com.litong.spring.boot.v222.secruty.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义登录失败处理
 */
@Component
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
      throws IOException, ServletException {
    log.info("登录失败");
    // 这里处理登录失败后就会输出错误信息
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.setContentType("application/json;charset-UTF-8");
    response.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
  }
}