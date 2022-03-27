package com.ppnt.spring.boot.demo.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@Configuration
public class RedisConfig {
  @Autowired
  RedisConnectionFactory redisConnectionFactory;

  @Bean
  public CacheManager cacheManager() {
    //defaultCacheConfig
    RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();

    //serializeValueWith
    SerializationPair<Object> serializeValuesWith = RedisSerializationContext.SerializationPair
        .fromSerializer((new GenericJackson2JsonRedisSerializer()));
    //serializeKeysWith
    SerializationPair<String> serializeKeysWith = RedisSerializationContext.SerializationPair
        .fromSerializer((new StringRedisSerializer()));

    //cacheKeyPrefix
    CacheKeyPrefix cacheKeyPrefix = (name) -> name + ":";
    RedisCacheConfiguration redisCacheConfiguration = defaultCacheConfig
        // 设置缓存有效期1小时
        .entryTtl(Duration.ofHours(1))
        //k v serialze
        .serializeKeysWith(serializeKeysWith).serializeValuesWith(serializeValuesWith)
        //computePrefixWith
        .computePrefixWith(cacheKeyPrefix);

    //nonLockingRedisCacheWriter
    RedisCacheWriter nonLockingRedisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
    RedisCacheManagerBuilder builder = RedisCacheManager.builder(nonLockingRedisCacheWriter);
    
    //cacheDefaults
    builder.cacheDefaults(redisCacheConfiguration);
    //build
    return builder.build();
  }
}