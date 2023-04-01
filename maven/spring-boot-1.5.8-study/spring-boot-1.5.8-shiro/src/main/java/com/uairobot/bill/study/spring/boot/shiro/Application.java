package com.uairobot.bill.study.spring.boot.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

import top.ppnt.spring.boot.controller.PpntSpringBootControllerConstants;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    // value
    value = { "com.uairobot.bill.study.spring.boot.shiro", PpntSpringBootControllerConstants.PACKAGE_NAME },
    //
    excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) }
//
)
public class Application {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(Application.class, args);
    //SpringApplicationWrapper.run(Application.class, args,true);
    long end = System.currentTimeMillis();
    System.out.println((end-start)+"ms");
  }
}
