package com.litong.java.spring.boot.thread.pool.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.litong.java.spring.boot.thread.pool.service.AsyncService;

@Service
public class AsyncServiceImpl implements AsyncService {

  private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

  @Async("taskExecutor")
  @Override
  public void executeAsync() {
    logger.info("start executeAsync");
    try {
      System.out.println("当前运行的线程名称：" + Thread.currentThread().getName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    logger.info("end executeAsync");
  }

}