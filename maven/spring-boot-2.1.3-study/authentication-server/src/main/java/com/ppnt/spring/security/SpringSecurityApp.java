package com.ppnt.spring.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import top.ppnt.spring.boot.config.RsaKeyProperties;

/**
 * @author create by Ping E Lee on 2022年3月31日 下午7:02:43 
 *
 */
@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
@MapperScan("top.ppnt.spring.boot.mapper")
public class SpringSecurityApp {
  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityApp.class, args);
  }
}
