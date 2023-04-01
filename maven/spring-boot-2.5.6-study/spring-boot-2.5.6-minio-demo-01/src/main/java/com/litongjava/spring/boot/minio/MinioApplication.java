package com.litongjava.spring.boot.minio;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

import top.ppnt.spring.boot.utils.startup.StartupUtils;

@SpringBootApplication
public class MinioApplication{

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ConfigurableApplicationContext ctx = SpringApplicationWrapper.run(MinioApplication.class, args);
    StartupUtils.info(start, ctx);
  }
}