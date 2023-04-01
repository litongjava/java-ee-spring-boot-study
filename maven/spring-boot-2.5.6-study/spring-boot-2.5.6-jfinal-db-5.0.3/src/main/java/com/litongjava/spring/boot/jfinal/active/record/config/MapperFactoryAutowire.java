package com.litongjava.spring.boot.jfinal.active.record.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;



@Configuration
public class MapperFactoryAutowire {

  @Bean
  public MapperFactory getMapperFactory() {
    return new DefaultMapperFactory.Builder().build();
  }
}