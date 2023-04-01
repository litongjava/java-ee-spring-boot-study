package com.litong.spring.boot.v158.web.wx.login.constants;

public class WxConstants {

  public static final String LOGIN_TYPE_WECHAT = null;

  // 请求此地址即跳转到二维码登录界面
  public static final String AUTHORIZATION_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";

  // 获取用户 openid 和access——toke 的 URL
  public static final String ACCESSTOKE_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

  public static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";

  public static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

  public static final String APP_ID = "xxxxxx";
  public static final String APP_SECRET = "xxxxxx";
  public static final String SCOPE = "snsapi_login";
}
