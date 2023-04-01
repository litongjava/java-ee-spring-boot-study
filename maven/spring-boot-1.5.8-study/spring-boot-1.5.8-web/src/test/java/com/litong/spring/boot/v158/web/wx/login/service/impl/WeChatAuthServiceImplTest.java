package com.litong.spring.boot.v158.web.wx.login.service.impl;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.litong.spring.boot.v158.web.wx.login.exception.ServiceException;

/**
 * @author bill robot
 * @date 2020年9月3日_上午7:51:12 
 * @version 1.0 
 * @desc
 */
public class WeChatAuthServiceImplTest {

  private WeChatAuthServiceImpl weChatAuthService=new WeChatAuthServiceImpl();

  @Test
  public void test() {
    try {
      String authorizationUrl = weChatAuthService.getAuthorizationUrl();
      System.out.println(authorizationUrl);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * getAccessToken
   */
  @Test
  public void test2() {
    try {
      String accessToken = weChatAuthService.getAccessToken("code");
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

}
