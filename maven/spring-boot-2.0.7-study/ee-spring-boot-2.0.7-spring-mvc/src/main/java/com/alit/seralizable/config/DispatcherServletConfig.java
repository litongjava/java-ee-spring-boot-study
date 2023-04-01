package com.alit.seralizable.config;

import java.io.File;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.DispatcherServlet;

public class DispatcherServletConfig {
  @Autowired
  private Environment e;

  @Bean
  public ServletRegistrationBean<DispatcherServlet> servletRegistrationBean() {
    String contextConfigLocation = e.getProperty("spring.mvc.xml");
    System.out.println("spring.mvc.xml:" + contextConfigLocation);
    String[] split = contextConfigLocation.split(":");
    File file = null;
    if (split[0].equals("classpath")) {
      URL resource = this.getClass().getClassLoader().getResource("");
      file = new File(resource.getFile() + split[1]);
    } else if (split[0].equals("file")) {
      file = new File(split[1]);
    }
    if (file.exists()) {
      System.out.println(file.getAbsolutePath() + "存在");
    } else {
      System.out.println(file.getAbsolutePath() + "不存在");
    }

    DispatcherServlet d = new DispatcherServlet();
    // 注册Servlet并添加拦截后缀
    String[] urlPattern = { "*.service", "*.do" };
    ServletRegistrationBean<DispatcherServlet> r = new ServletRegistrationBean<DispatcherServlet>(d, urlPattern);
    // 设置加载的配置文件
    r.addInitParameter("contextConfigLocation", contextConfigLocation);
    // 设置Servlet和服务器同时启动
    r.setLoadOnStartup(0);
    return r;
  }
}