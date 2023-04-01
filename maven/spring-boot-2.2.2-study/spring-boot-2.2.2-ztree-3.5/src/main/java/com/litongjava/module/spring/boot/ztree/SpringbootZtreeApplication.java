package com.litongjava.module.spring.boot.ztree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author create by ping-e-lee on 2021年4月11日 下午6:36:32 
 * @version 1.0 
 * @desc
 */
@SpringBootApplication
@MapperScan("com.litongjava.module.spring.boot.ztree.mapper")
public class SpringbootZtreeApplication {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ConfigurableApplicationContext run = SpringApplication.run(SpringbootZtreeApplication.class, args);
    info(start,run);
  }
  
  private static void info(long start,ConfigurableApplicationContext ctx) {
    // 获取tomcat server
    TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) ctx
        .getBean("tomcatServletWebServerFactory");
    // 获取启动地址和端口
    int port = tomcatServletWebServerFactory.getPort();
    String contextPath = tomcatServletWebServerFactory.getContextPath();
    Environment environment = ctx.getBean(Environment.class);
    String projectName = environment.getProperty("spring.application.name");
    long end = System.currentTimeMillis();
    // 输出地址和端口
    System.out.println(projectName + "完成启动共使用了:" + (end - start) + "ms");
    System.out.println("http://127.0.0.1:"+port+contextPath);
  }
}
