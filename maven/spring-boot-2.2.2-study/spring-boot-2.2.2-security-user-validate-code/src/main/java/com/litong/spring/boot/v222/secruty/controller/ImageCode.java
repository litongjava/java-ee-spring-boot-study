package com.litong.spring.boot.v222.secruty.controller;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 验证码对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageCode {
  // 验证码
  private String code;
  // 过期时间
  private LocalDateTime expireTime;
  // 图片
  private BufferedImage image;

  public boolean isExpried() {
    return LocalDateTime.now().isAfter(expireTime);
  }

  public ImageCode(String code, int expireTime, BufferedImage image) {
    this.code = code;
    this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    this.image = image;
  }
}