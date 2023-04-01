package com.litong.spring.boot.v222.secruty.controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码校验控制器
 *
 */
@RestController
public class ValidateCodeController {
  public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  @GetMapping("/code/image")
  public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ImageCode imageCode = ImageCodeUtils.createImageCode(request);
    // 将验证码存入session
    sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
    // 输出图片
    ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
  }
}