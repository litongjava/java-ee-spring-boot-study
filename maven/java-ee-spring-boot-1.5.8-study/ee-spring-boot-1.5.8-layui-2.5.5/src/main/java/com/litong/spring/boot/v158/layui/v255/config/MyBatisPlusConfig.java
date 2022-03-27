package com.litong.spring.boot.v158.layui.v255.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * @desc: MybatisPlus配置类
 */
@Configuration
public class MyBatisPlusConfig {
  /**
  * 分页插件
  */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
  	return new PaginationInterceptor();
  }
}