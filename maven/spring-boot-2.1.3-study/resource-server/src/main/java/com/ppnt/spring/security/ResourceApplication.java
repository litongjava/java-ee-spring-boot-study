package com.ppnt.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import top.ppnt.spring.boot.config.RsaPublicKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaPublicKeyProperties.class) // 将配置类放入Spring容器中
public class ResourceApplication {
  public static void main(String[] args) {
    SpringApplication.run(ResourceApplication.class, args);
  }
}