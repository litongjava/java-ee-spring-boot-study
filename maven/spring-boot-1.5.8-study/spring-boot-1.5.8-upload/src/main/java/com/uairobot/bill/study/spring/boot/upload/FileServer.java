package com.uairobot.bill.study.spring.boot.upload;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import com.litong.utils.ip.IPUtil;

@SpringBootApplication
public class FileServer {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication app = new SpringApplication(FileServer.class);
    app.setBannerMode(Banner.Mode.OFF);
    ApplicationContext context = app.run(args);
    info(start, context);
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
  }
}