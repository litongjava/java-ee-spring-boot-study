package com.litong.study.spring.boot207.quratz.quratz;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
  /**
   * 创建jobDetail
   */
  @SuppressWarnings("unchecked")
  @Bean(name = "mjtTaskJobDetail")
  public JobDetail mjtTaskJobDetail() {
    Class<Job> forName=null;
    try {
      forName = (Class<Job>) Class.forName("com.litong.study.spring.boot207.quratz.quratz.MjtTask");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return JobBuilder.newJob(forName).withIdentity("mjtTask").storeDurably().build();
  }

  /**
   * 创建trigger
   */
  @Bean
  public Trigger umjtTaskTrigger() {
    //每5秒执行一次
    String cronExp = "/5 * * * * ?"; 
    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExp);
    return TriggerBuilder.newTrigger().forJob(mjtTaskJobDetail()).withIdentity("mjtTask").withSchedule(scheduleBuilder)
        .build();
  }
}