package com.litong.java.spring.boot.thread.pool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litong.java.spring.boot.thread.pool.dataobject.ResultVO;
import com.litong.java.spring.boot.thread.pool.service.AsyncService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("thread")
@Slf4j
public class ThreadController {

  @Autowired
  private AsyncService asyncService;

  @Autowired
  private TaskExecutor taskExecutor;

  @RequestMapping("execute")
  public ResultVO<Void> execute() {
    taskExecutor.execute(() -> {
      int sum = 0;
      for (int i = 0; i < 100; i++) {
        sum += i;
      }
      log.info("sum:{}", sum);
    });
    return ResultVO.getSuccess("OK");
  }

  @GetMapping("async")
  public ResultVO<Void> async() {
    // 调用service层的任务
    asyncService.executeAsync();
    return ResultVO.getSuccess("OK");
  }

}