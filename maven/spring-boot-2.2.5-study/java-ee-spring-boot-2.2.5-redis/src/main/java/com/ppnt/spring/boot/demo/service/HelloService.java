package com.ppnt.spring.boot.demo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ppnt.spring.boot.demo.dto.HelloRespDTO;

@Service
public class HelloService {

  @Cacheable(cacheNames = "employee", key = "'detail'+#id")
  public HelloRespDTO helloCache(String id) {
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