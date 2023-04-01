package com.liotngjava.spring.boot.redis.cluter.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liotngjava.spring.boot.redis.cluter.RedisClusterApplication;

import redis.clients.jedis.JedisCluster;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisClusterApplication.class)
public class RedisClusterWorkApplicationTest {

  @Autowired
  private JedisCluster jedisCluster;

  @Test
  public void testRedisCluster() {
    String result = jedisCluster.set("name", "zhangfei");
    System.out.println("插入结果：" + result);
    String name = jedisCluster.get("name");
    System.out.println("查询结果：" + name);
  }

}