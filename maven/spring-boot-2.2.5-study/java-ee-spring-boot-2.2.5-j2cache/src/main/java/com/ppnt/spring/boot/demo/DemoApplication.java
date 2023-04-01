package com.ppnt.spring.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import top.ppnt.spring.boot.utils.startup.StartupUtils;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
  value = {"com.ppnt.spring.boot.demo", "top.ppnt.spring.boot.controller"},
  excludeFilters = {
    @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
    @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)
  })
public class DemoApplication {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
    StartupUtils.info(start, ctx);
    
  }
}