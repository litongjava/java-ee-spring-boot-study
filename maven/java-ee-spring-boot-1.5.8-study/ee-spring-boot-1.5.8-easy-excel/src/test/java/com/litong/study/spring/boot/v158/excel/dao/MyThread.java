package com.litong.study.spring.boot.v158.excel.dao;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.util.CollectionUtils;

/**
 * 自定义线程
 */
public class MyThread extends Thread {
  private FeedbackEmpMapper feedbackEmpMapper;
  private List<FeedbackEmp> list;
  private CountDownLatch countDownLatch;

  MyThread(FeedbackEmpMapper feedbackEmpMapper, List<FeedbackEmp> list, CountDownLatch countDownLatch) {
    this.feedbackEmpMapper = feedbackEmpMapper;
    this.list = list;
    this.countDownLatch = countDownLatch;
  }

  @Override
  public void run() {
    if (!CollectionUtils.isEmpty(list)) {
      feedbackEmpMapper.insertBatch(list);
    }
    countDownLatch.countDown();
  }
}