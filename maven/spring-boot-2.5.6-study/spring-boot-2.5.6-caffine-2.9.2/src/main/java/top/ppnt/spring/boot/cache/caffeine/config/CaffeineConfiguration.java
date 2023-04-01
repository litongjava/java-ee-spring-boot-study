package top.ppnt.spring.boot.cache.caffeine.config;

import java.util.concurrent.TimeUnit;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching // 开启缓存
public class CaffeineConfiguration {
  @Bean
  public Caffeine caffeineConfig() {
    @NonNull
    Caffeine<Object, Object> caffeine = Caffeine.newBuilder();
    //配置Caffeine缓存行为（例如到期，缓存大小限制等）
    caffeine.expireAfterWrite(60, TimeUnit.MINUTES).maximumSize(1000);
    return caffeine;
  }

  @Bean
  public CacheManager cacheManager(Caffeine caffeine) {
    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
    caffeineCacheManager.setCaffeine(caffeine);
    return caffeineCacheManager;
  }
}
