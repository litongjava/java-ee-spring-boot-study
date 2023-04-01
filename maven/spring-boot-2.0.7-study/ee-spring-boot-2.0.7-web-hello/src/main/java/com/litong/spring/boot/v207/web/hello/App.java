package com.litong.spring.boot.v207.web.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@ComponentScan(
  value = "com.litong.spring.boot.v207.web.hello",
  excludeFilters = {
  @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
  @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
@Import(ImportAutoConfiguration.class)
public class App {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(App.class, args);
    long end = System.currentTimeMillis();
    System.out.println("======================================" + (end - start) + "ms");
  }
}
