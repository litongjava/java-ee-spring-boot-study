package com.biillrobot.study.spring.validte.handler;

import lombok.Data;

@Data
public class ResultInfo<T> {
  private int code;
  private String message;
  private T body;

  public ResultInfo(int code, String message, T body) {
    this.code = code;
    this.message = message;
    this.body = body;
  }

  public ResultInfo(int code, String message) {
    this(code, message, null);
  }

  public ResultInfo(String message) {
    this(200, message, null);
  }
}
