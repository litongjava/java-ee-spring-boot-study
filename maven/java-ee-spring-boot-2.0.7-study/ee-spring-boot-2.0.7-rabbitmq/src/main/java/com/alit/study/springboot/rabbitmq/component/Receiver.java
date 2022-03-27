package com.alit.study.springboot.rabbitmq.component;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = { "hello" })
public class Receiver {

  @RabbitHandler
  public void handler(String content) {
    System.out.println("接收到：" + content);
  }
}