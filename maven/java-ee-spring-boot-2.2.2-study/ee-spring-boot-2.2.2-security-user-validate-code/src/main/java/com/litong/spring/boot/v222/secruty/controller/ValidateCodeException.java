package com.litong.spring.boot.v222.secruty.controller;

import org.springframework.security.core.AuthenticationException;

/**
 *  自定义异常
 */
@SuppressWarnings("serial")
public class ValidateCodeException extends AuthenticationException {
  public ValidateCodeException(String msg) {
    super(msg);
  }
}