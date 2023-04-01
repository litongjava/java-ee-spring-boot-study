package com.liotngjava.spring.boot.redis.cluter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("")
public class IndexController {

  @Autowired
  private JedisCluster jedisCluster;

  @RequestMapping("")
  public String index() {
    return "index";
  }

  @RequestMapping("testRedisCluster")
  public void testRedisCluster() {
    String result = jedisCluster.set("name", "zhangfei");
    System.out.println("插入结果：" + result);
    String name = jedisCluster.get("name");
    System.out.println("查询结果：" + name);
  }
}