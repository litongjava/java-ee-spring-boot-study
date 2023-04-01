package com.liotngjava.spring.boot.redis.cluter.config;

/**
 * @author create by Ping E Lee on Sep 16, 2022 1:22:35 PM 
 *
 */

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClusterTest {

  public static void main(String[] args) {
    Set<HostAndPort> nodes = new HashSet<>();
    nodes.add(new HostAndPort("106.14.75.227", 7001));
    nodes.add(new HostAndPort("106.14.75.227", 7002));
    nodes.add(new HostAndPort("106.14.75.227", 7003));
    nodes.add(new HostAndPort("106.14.75.227", 7004));
    nodes.add(new HostAndPort("106.14.75.227", 7005));
    nodes.add(new HostAndPort("106.14.75.227", 7006));

    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(500);
    config.setMinIdle(2);
    config.setMaxIdle(500);
    config.setMaxWaitMillis(10000);
    config.setTestOnBorrow(true);
    config.setTestOnReturn(true);

    JedisCluster jedisCluster = new JedisCluster(nodes, 10000, 10000, 100, "password", config);
//    JedisCluster jedisCluster = new JedisCluster(nodes);
    System.out.println(jedisCluster.set("username", "Ping"));
    System.out.println(jedisCluster.get("username"));

    jedisCluster.close();
  }

}
