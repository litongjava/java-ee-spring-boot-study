//package com.liotngjava.spring.boot.redis.cluter.config;
//
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Configuration
//@EnableCaching
//public class RedisConfig extends CachingConfigurerSupport {
//
//  @Bean
//  @SuppressWarnings(value = { "unchecked", "rawtypes" })
//  public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
//    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//    redisTemplate.setConnectionFactory(connectionFactory);
//    Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
//
//    ObjectMapper mapper = new ObjectMapper();
//    mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//    mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
//        JsonTypeInfo.As.PROPERTY);
//
//    serializer.setObjectMapper(mapper);
//
//    // 使用StringRedisSerializer来序列化和反序列化redis的key值
//    redisTemplate.setKeySerializer(new StringRedisSerializer());
//    redisTemplate.setValueSerializer(serializer);
//
//    // Hash的key也采用StringRedisSerializer的序列化方式
//    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//    redisTemplate.setHashValueSerializer(serializer);
//
//    redisTemplate.afterPropertiesSet();
//    return redisTemplate;
//  }
//
//}
