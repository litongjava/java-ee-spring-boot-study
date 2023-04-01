package com.ppnt.spring.boot.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ppnt.spring.boot.demo.dto.HelloRespDTO;

@Service
public class HelloService {
  private static final Logger log = LoggerFactory.getLogger(HelloService.class);
  @Cacheable(cacheNames = "employee", key = "'detail'+#id")
  public HelloRespDTO helloCache(String id) {
    log.info("id:{}", id);
    HelloRespDTO hello = new HelloRespDTO();
    hello.setName("ben");
    hello.setAddress("广州市白云区");
    hello.setAge(18);
    return hello;
  }

  @CacheEvict(value = "employee", key = "'detail'+#id")
  public HelloRespDTO helloClear(String id) {
    HelloRespDTO hello = new HelloRespDTO();
    hello.setName("ben");
    hello.setAddress("广州市白云区");
    hello.setAge(18);
    return hello;
  }
}