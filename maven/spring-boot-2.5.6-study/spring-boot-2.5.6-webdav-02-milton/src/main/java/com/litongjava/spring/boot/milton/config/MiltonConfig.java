package com.litongjava.spring.boot.milton.config;

import java.io.File;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.milton.config.HttpManagerBuilder;
import io.milton.http.ResourceFactory;
import io.milton.http.fs.FileSystemResourceFactory;
import io.milton.http.fs.NullSecurityManager;
import io.milton.http.http11.DefaultHttp11ResponseHandler;
import io.milton.servlet.SpringMiltonFilter;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableConfigurationProperties(MiltonProperties.class)
@Slf4j
public class MiltonConfig {
  private MiltonProperties miltonProperties;

  public MiltonConfig(MiltonProperties miltonProperties) {
    this.miltonProperties = miltonProperties;
  }

  /**
   * 注册bean
   * @return
   */
  @Bean
  FilterRegistrationBean<SpringMiltonFilter> filterRegistrationBean() {
    FilterRegistrationBean<SpringMiltonFilter> bean = new FilterRegistrationBean<>();
    bean.setFilter(springMiltonFilterBean());
    //设置拦截目录
    bean.addUrlPatterns("/*");
    return bean;
  }

  @Bean
  SpringMiltonFilter springMiltonFilterBean() {
    //return new SpringMiltonFilterBean();
    return new io.milton.servlet.SpringMiltonFilter();
  }

  @Bean
  @ConditionalOnProperty("milton.filesystem_root")
  ResourceFactory resourceFactory() {
    FileSystemResourceFactory factory = new FileSystemResourceFactory();
    factory.setAllowDirectoryBrowsing(true);
    factory.setRoot(new File(miltonProperties.getFilesystemRoot()));
    factory.setSecurityManager(new NullSecurityManager());
    ensureDataDirectory(miltonProperties.getFilesystemRoot());
    return factory;
  }

  @Bean
  HttpManagerBuilder httpManagerBuilder() {
    HttpManagerBuilder builder = new HttpManagerBuilder();
    builder.setResourceFactory(resourceFactory());
    builder.setBuffering(DefaultHttp11ResponseHandler.BUFFERING.whenNeeded);
    builder.setEnableCompression(false);
    return builder;
  }

  /**
   * 确保存放数据的目录存在
   */
  private static void ensureDataDirectory(String path) {
    File file = new File(path);
    log.info("{}",file.getAbsolutePath());
    if (file.exists()) {
      return;
    }
    boolean b = file.mkdir();
    if (!b) {
      throw new IllegalStateException("create directory fail");
    }
  }
}
