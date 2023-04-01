package com.alit.seralizable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import com.alit.seralizable.config.ApplicationContextAwareImpl;
import com.alit.seralizable.config.DispatcherServletConfig;
import com.alit.seralizable.config.ResourcesStaticServletConfig;
import com.litong.utils.ip.IPUtil;

@SpringBootConfiguration
@Import({
    // webserver
    ServletWebServerFactoryAutoConfiguration.class,
    // dispather
    DispatcherServletConfig.class,
    // ApplicationContextAwareImpl
    ApplicationContextAwareImpl.class,
    // ResourceProperties
    ResourcesStaticServletConfig.class })
public class Application {
  private static Environment e;

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(Application.class, args);
    long end = System.currentTimeMillis();
    System.out.println("启动完成,使用了" + (end - start) + "ms");
    e = ApplicationContextAwareImpl.getBean(Environment.class);
    IPUtil.printAccessUrl(e.getProperty("server.port", Integer.class), e.getProperty("server.servlet.context-path"));
  }
}