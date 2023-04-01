package com.litongjava.spring.boot.live.reload;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import top.ppnt.spring.boot.utils.startup.StartupUtils;

@SpringBootApplication
public class LiveReloadApplication{

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ConfigurableApplicationContext ctx = SpringApplication.run(LiveReloadApplication.class, args);
    StartupUtils.info(start, ctx);
  }
}