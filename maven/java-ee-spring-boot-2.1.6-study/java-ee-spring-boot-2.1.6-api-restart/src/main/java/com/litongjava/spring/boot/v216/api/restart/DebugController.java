package com.litongjava.spring.boot.v216.api.restart;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("debug")
public class DebugController {
  @Value("${spring.application.name}")
  String name;

  @RequestMapping("isActive")
  public boolean isActive() {
    return ExampleRestartApplication.context.isActive();
  }

  @RequestMapping("close")
  public boolean close() {
    ExampleRestartApplication.context.close();
    return true;
  }

  /**
   * 测试结果:不能重启项目
   * @return
   */
  @RequestMapping("refresh")
  public boolean refresh() {
    new Thread(() -> {
      ExampleRestartApplication.context.refresh();
    });
    return true;
  }

  @RequestMapping("closeAndStart")
  public void closeAndStart() {
    new Thread(() -> {
      ExampleRestartApplication.context.close();
      ExampleRestartApplication.context.start();
    }).start();
  }

  @RequestMapping("/restart")
  public String restart() {
    log.info("restat spring.application.name:\t{}", name);
    // 创建线程池
    ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1);
    ThreadPoolExecutor.DiscardOldestPolicy handler = new ThreadPoolExecutor.DiscardOldestPolicy();
    ExecutorService threadPool = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, workQueue, handler);

    // 使用线程池执行线程
    threadPool.execute(() -> {
      ExampleRestartApplication.context.close();
      ExampleRestartApplication.context = SpringApplication.run(ExampleRestartApplication.class, ExampleRestartApplication.args);
    });
    // 关闭线程池
    threadPool.shutdown();
    return "spring.application.name:" + name;
  }
}