package com.litong.spring.boot.v158.layui.v255;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationHome;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * @author bill robot
 * @date 2020年5月27日_下午9:26:32 
 * @version 1.0 
 * @desc
 */
@SpringBootApplication
@MapperScan({ "com.litong.spring.boot.v158.layui.v255.dao", "com.litong.spring.boot.v158.layui.v255.mapper" })
public class App {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
    info(start, ctx);

  }

  private static void info(long start, ApplicationContext ctx) {
    // 获取tomcat server 属性
    ServerProperties sp = ctx.getBean(ServerProperties.class);
    // 获取启动地址和端口
    int port = sp.getPort();
    String contextPath = sp.getContextPath();
    Environment environment = ctx.getBean(Environment.class);
    String projectName = environment.getProperty("spring.application.name");
    if (StringUtils.isEmpty(projectName))
      projectName = "file-server";
    String thisUrl = IPUtil.getThisUrl(port, contextPath);
    long end = System.currentTimeMillis();
    // 输出地址和端口
    System.out.println(projectName + "完成启动共使用了:" + (end - start) + "ms");
    System.out.println(thisUrl);
    ApplicationHome applicationHome = new ApplicationHome();
    System.out.println(applicationHome.getDir().toString());
  }
}
