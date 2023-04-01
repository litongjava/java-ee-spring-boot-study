package top.ppnt.spring.boot.cache.caffeine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.ppnt.spring.boot.cache.caffeine.services.CaffeineTestService;

@RestController
@RequestMapping("caffine/test")
public class CaffeineTestController {

  @Autowired
  private CaffeineTestService caffeineTestService;
  
  @RequestMapping("caffeinSetValue")
  public String caffeinSetValue(Integer number) {
    return caffeineTestService.caffeinSetValue(number);
  }
}
