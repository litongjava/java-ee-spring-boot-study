package com.alit.seralizable.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.alit.seralizable.servlet.ResourcesStaticServlet;

public class ResourcesStaticServletConfig {
  @Bean // 返回的类型是bean的类型,方法名是bean的name
  public ServletRegistrationBean<ResourcesStaticServlet> resourcesStaticServlet() {
    ResourcesStaticServlet servlet = new ResourcesStaticServlet();
    String[] urlPattern = { "/", "*.html", "*.css", "*.js", "*.jpg", "*.jpeg", "*.gif", "*.png", "*.ico", "*.pdf",
        "*.txt" };
    ServletRegistrationBean<ResourcesStaticServlet> r = new ServletRegistrationBean<ResourcesStaticServlet>(servlet,
        urlPattern);
    return r;
  }
}
