package com.litongjava.logbak.stomp.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LoggerQueue {
  // 队列大小
  public static final int QUEUE_MAX_SIZE = 10000;
  private static LoggerQueue alarmMessageQueue = new LoggerQueue();
  // 阻塞队列
  private BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

  private LoggerQueue() {
  }

  public static LoggerQueue getInstance() {
    return alarmMessageQueue;
  }

  // 消息入队
  public boolean push(String message) {
    // 队列满了就抛出异常，不阻塞
    return this.blockingQueue.add(message);
  }

  // 消息出队
  public String poll() {
    String result = null;
    try {
      result = this.blockingQueue.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return result;
  }
}