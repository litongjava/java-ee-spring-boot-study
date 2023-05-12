package com.litongjava.spring.boot.scheduing.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTasks {

  @Scheduled(fixedDelay = 3000)
  void demo01() throws InterruptedException {
    log.info("scheduled1 wait start");
    Thread.sleep(4000);
    log.info("scheduled1 wait end");
    log.info("scheduled task run....      " + Thread.currentThread().getName());
  }
}