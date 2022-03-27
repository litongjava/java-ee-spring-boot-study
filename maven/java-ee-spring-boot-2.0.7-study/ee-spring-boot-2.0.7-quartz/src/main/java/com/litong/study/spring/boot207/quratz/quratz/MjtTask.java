package com.litong.study.spring.boot207.quratz.quratz;

import java.time.LocalDateTime;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 定时任务执行的方法类
 */
public class MjtTask extends QuartzJobBean {

  @Override
  protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    LocalDateTime now = LocalDateTime.now();
    System.out.println(now + "_quartz定时任务方法");
  }
}