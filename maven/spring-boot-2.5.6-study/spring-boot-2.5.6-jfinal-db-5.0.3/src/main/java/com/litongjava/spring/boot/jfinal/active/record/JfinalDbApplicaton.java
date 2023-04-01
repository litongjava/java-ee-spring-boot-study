package com.litongjava.spring.boot.jfinal.active.record;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

import top.ppnt.spring.boot.utils.startup.StartupUtils;

@SpringBootApplication
public class JfinalDbApplicaton{

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ConfigurableApplicationContext ctx = SpringApplicationWrapper.run(JfinalDbApplicaton.class, args);
    StartupUtils.info(start, ctx);
  }
}