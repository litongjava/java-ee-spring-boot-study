package com.litongjava.spring.boot.milton;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;
import com.litongjava.spring.boot.milton.config.milton.MiltonServer;

import top.ppnt.spring.boot.utils.startup.StartupUtils;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ConfigurableApplicationContext ctx = SpringApplicationWrapper.run(Application.class, args);
    MiltonServer.initIpWhitelist();
    StartupUtils.info(start, ctx);
  }
}
