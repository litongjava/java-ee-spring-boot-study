package com.litongjava.webmagic;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 实现PipeLine和使用定时器
 */
@Component
@Slf4j
public class JpaPipeline implements Pipeline {

  //@Autowired
  //private JobInfoService jobInfoService;

  @Override
  public void process(ResultItems resultItems, Task task) {
    // 获取职位数据
    JobInfo jobInfo = resultItems.get("jobInfo");

    if (jobInfo != null) {
      log.info(jobInfo.toString());
      //jobInfoService.save(jobInfo);
    }
  }
}
