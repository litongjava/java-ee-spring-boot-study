package com.liotngjava.spring.boot.redis.cluter.controller;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("")
@Slf4j
public class IndexController {

  @Autowired
  private RedissonClient redissonClient;

  private void demo() {
    RBucket<Object> bucket = redissonClient.getBucket("string-demo");
    bucket.set("hello");
    System.out.println(bucket.get());
  }

  @RequestMapping("")
  public String index() {
    return "index";
  }

  @RequestMapping("testRedisCluster")
  public void testRedisCluster() {
    log.info(redissonClient.toString());
  }
}