package com.liotngjava.spring.boot.redis.cluter.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.redisson.api.RBuckets;
import org.redisson.api.RKeys;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liotngjava.spring.boot.redis.cluter.utils.RedisUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("demp/redis")
@Slf4j
public class RedisDemoController {

  @Autowired
  private RedisUtils redisUtils;

  private static String DEMO_STR = "demoStr";

  @PostMapping("/learnRedisson")
  public void learnRedisson() {
    // 三种数据结构使用示例
    strDemo();
    hashDemo();
    listDemo();
    setDemo();
    getKeys();
  }

  private void getKeys() {
    RKeys keys = redisUtils.getKeys();
    Iterable<String> allKeys = keys.getKeys();
    StringBuilder sb = new StringBuilder();
    for (String key : allKeys) {
      sb = sb.append(key).append(",");
    }
    log.info("所有的key：{}", sb.substring(0, sb.length() - 1));
    // 模糊查询以 map 打头的所有 key
    allKeys = keys.getKeysByPattern("map*");
    sb = new StringBuilder();
    for (String key : allKeys) {
      sb = sb.append(key).append(",");
    }
    log.info("模糊匹配到的key：{}", sb.substring(0, sb.length() - 1));
  }

  /**
   * Hash类型
   */
  private void hashDemo() {
    RMap<Object, Object> map = redisUtils.getMap("mapDemo");
    map.put("demoId1", "123");
    map.put("demoId100", "13000");
    Object demoId1Obj = map.get("demoId1");
    log.info("Hash 测试数据：{}", demoId1Obj);
  }

  /**
   * String 数据类型
   */
  private void strDemo() {
    redisUtils.setStr(DEMO_STR, "Hello, String.");
    log.info("String 测试数据：{}", redisUtils.getStr(DEMO_STR));
    redisUtils.setStr("myBucket", "myBucketIsXxx");
    RBuckets buckets = redisUtils.getBuckets();
    Map<String, String> foundBuckets = buckets.get("myBucket*");
    log.info("foundBuckets:{}",foundBuckets);
    Map<String, Object> map = new HashMap<>();
    map.put("myBucket1", "value1");
    map.put("myBucket2", 30L);

    // 同时保存全部通用对象桶。
    buckets.set(map);
    Map<String, String> loadedBuckets = buckets.get("myBucket1", "myBucket2", "myBucket3");
    log.info("跨桶String 测试数据：{}", loadedBuckets);
    map.put("myBucket3", 320L);
  }

  /**
   * List数据类型
   */
  private void listDemo() {
    RList<String> list = redisUtils.getList("listDemo");
    list.add("listValue1");
    list.add("listValue2");

    log.info("List 测试数据：{}", list.get(1));
  }

  /**
   * Set 测试
   */
  private void setDemo() {
    RSet<String> set = redisUtils.getSet("setKey");
    set.add("value777");
    log.info("Set 测试数据");
    Iterator<String> iterator = set.iterator();
    while (iterator.hasNext()) {
      String next = iterator.next();
      log.info(next);
    }
  }
}
