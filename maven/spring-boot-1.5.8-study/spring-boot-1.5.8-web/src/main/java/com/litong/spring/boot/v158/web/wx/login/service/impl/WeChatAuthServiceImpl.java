package com.litong.spring.boot.v158.web.wx.login.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONObject;
import com.litong.spring.boot.v158.web.wx.login.constants.WxConstants;
import com.litong.spring.boot.v158.web.wx.login.exception.ServiceException;
import com.litong.spring.boot.v158.web.wx.login.service.WeChatAuthService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WeChatAuthServiceImpl implements WeChatAuthService {

  @Autowired
  private RestTemplate restTemplate;

  private String callbackUrl = "https://www.xxx.cn/auth/wechat"; // 回调域名

  public String getAuthorizationUrl() throws UnsupportedEncodingException {
    callbackUrl = URLEncoder.encode(callbackUrl, "utf-8");
    String url = String.format(WxConstants.AUTHORIZATION_URL, WxConstants.APP_ID, callbackUrl, WxConstants.SCOPE, System.currentTimeMillis());
    return url;
  }

  public String getAccessToken(String code) throws ServiceException {
    
    String url = String.format(WxConstants.ACCESSTOKE_OPENID_URL, WxConstants.APP_ID, WxConstants.APP_SECRET, code);
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
    URI uri = builder.build().encode().toUri();

    String resp = restTemplate.getForObject(uri, String.class);
    log.error("getAccessToken resp = " + resp);
    if (resp.contains("openid")) {
      JSONObject jsonObject = JSONObject.parseObject(resp);
      String access_token = jsonObject.getString("access_token");
      String openId = jsonObject.getString("openid");

      JSONObject res = new JSONObject();
      res.put("access_token", access_token);
      res.put("openId", openId);
      res.put("refresh_token", jsonObject.getString("refresh_token"));

      return res.toJSONString();
    } else {
      throw new ServiceException("获取token失败，msg = " + resp);
    }
  }

  // 微信接口中，token和openId是一起返回，故此方法不需实现
  public String getOpenId(String accessToken) {
    return null;
  }

  public JSONObject getUserInfo(String accessToken, String openId) throws ServiceException {
    String url = String.format(WxConstants.USER_INFO_URL, accessToken, openId);
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
    URI uri = builder.build().encode().toUri();

    String resp = restTemplate.getForObject(uri, String.class);
    log.error("getUserInfo resp = " + resp);
    if (resp.contains("errcode")) {
      throw new ServiceException("获取用户信息错误，msg = " + resp);
    } else {
      JSONObject data = JSONObject.parseObject(resp);

      JSONObject result = new JSONObject();
      result.put("id", data.getString("unionid"));
      result.put("nickName", data.getString("nickname"));
      result.put("avatar", data.getString("headimgurl"));

      return result;
    }
  }

  // 微信的token只有2小时的有效期，过时需要重新获取，所以官方提供了
  // 根据refresh_token 刷新获取token的方法，本项目仅仅是获取用户
  // 信息，并将信息存入库，所以两个小时也已经足够了
  public String refreshToken(String refresh_token) {

    String url = String.format(WxConstants.REFRESH_TOKEN_URL, WxConstants.APP_ID, refresh_token);

    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
    URI uri = builder.build().encode().toUri();

    ResponseEntity<JSONObject> resp = restTemplate.getForEntity(uri, JSONObject.class);
    JSONObject jsonObject = resp.getBody();

    String access_token = jsonObject.getString("access_token");
    return access_token;
  }
}