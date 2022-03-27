package com.litong.spring.boot.v158.web.wx.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.litong.spring.boot.v158.web.wx.login.constants.WxConstants;
import com.litong.spring.boot.v158.web.wx.login.model.KmsUser;
import com.litong.spring.boot.v158.web.wx.login.service.UserService;
import com.litong.spring.boot.v158.web.wx.login.service.WeChatAuthService;
import com.litong.utils.vo.JsonBean;

@RestController
@RequestMapping("auth")
public class AuthLoginController {
  @Autowired
  private UserService userService;
  @Autowired
  private WeChatAuthService weChatAuthService;

  /**
   * 返回用户扫码登陆
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/wxLoginPage", method = RequestMethod.GET)
  public JsonBean<String> wxLoginPage() throws Exception {
    String uri = weChatAuthService.getAuthorizationUrl();
    JsonBean<String> jsonBean = new JsonBean<>();
    jsonBean.setData(uri);
    return jsonBean;
  }

  /**
   * 微信回调地址,包含临时票据code
   * @param code
   * @param request
   * @param response
   * @throws Exception
   */
  @RequestMapping(value = "/wechat")
  public void callback(String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
    String result = weChatAuthService.getAccessToken(code);
    JSONObject jsonObject = JSONObject.parseObject(result);

    String access_token = jsonObject.getString("access_token");
    String openId = jsonObject.getString("openId");
    // String refresh_token = jsonObject.getString("refresh_token");

    // 保存 access_token 到 cookie，两小时过期
    Cookie accessTokencookie = new Cookie("accessToken", access_token);
    accessTokencookie.setMaxAge(60 * 2);
    response.addCookie(accessTokencookie);

    Cookie openIdCookie = new Cookie("openId", openId);
    openIdCookie.setMaxAge(60 * 2);
    response.addCookie(openIdCookie);

    // 根据openId判断用户是否已经登陆过
    KmsUser user = userService.getUserByCondition(openId);

    if (user == null) {
      response
          .sendRedirect(request.getContextPath() + "/student/html/index.min.html#/bind?type=" + WxConstants.LOGIN_TYPE_WECHAT);
    } else {
      // 如果用户已存在，则直接登录
      response.sendRedirect(request.getContextPath() + "/student/html/index.min.html#/app/home?open_id=" + openId);
    }
  }
}
