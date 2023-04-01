package top.ppnt.spring.boot.cache.caffeine.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CaffeineTestService {

  @Cacheable(value = "caffeinSet_Value")
  public String caffeinSetValue(Integer number) {
    log.info("开始计算");
    String str = number % 2 == 0 ? number + "是偶数" : number + "是奇数";
    log.info("结束计算");
    return str;
  }
}
