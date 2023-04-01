package com.litong.study.spring.boot207.quratz.quratz;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class SpringCornTask {
  // 每一秒执行一次
  @Scheduled(cron = "0/1 * * * * ?")
  public void task() {
    LocalDateTime now = LocalDateTime.now();
    System.out.println(now + "_spring定时任务方法");
  }
}