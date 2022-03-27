package com.litong.spring.boot.v158.web.wx.login.service;

import com.alibaba.fastjson.JSONObject;

public interface WeChatAuthService{
  public JSONObject getUserInfo(String accessToken, String openId) throws Exception;
  public String getAuthorizationUrl() throws Exception;
  public String getAccessToken(String code) throws Exception;
}