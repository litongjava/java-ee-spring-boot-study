package com.litongjava.spring.boot.scheduing.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
@Slf4j
public class ScheduledTasks2 {

  @Scheduled(fixedDelay = 3000)
  //@Scheduled(fixedRate = 3000)
  //@Scheduled(cron = "0/3 * * * * ?")
  //@Async(value = "myAsync")
  void contextLoads() throws InterruptedException {
    log.info("scheduled1 wait start");
    Thread.sleep(4000);
    log.info("scheduled1 wait end");
    log.info("scheduled task run....      " + Thread.currentThread().getName());
  }

  //@Scheduled(cron = "0/3 * * * * ?")
  @Scheduled(fixedDelay = 3000)
  //@Scheduled(fixedRate = 3000)
  //@Async(value = "myAsync")
  void scheduled2() throws InterruptedException {
    log.info("scheduled2 wait start");
    Thread.sleep(4000);
    log.info("scheduled2 wait end");
    log.info("scheduled task2 run....      " + Thread.currentThread().getName());
  }
}