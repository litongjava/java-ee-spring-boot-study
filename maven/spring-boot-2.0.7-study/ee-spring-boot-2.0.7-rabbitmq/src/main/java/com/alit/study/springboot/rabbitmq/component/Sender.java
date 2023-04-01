package com.alit.study.springboot.rabbitmq.component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

  @Autowired
  private AmqpTemplate amqpTemplate;

  public void send() {
    String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String content = "hello:" + format;
    amqpTemplate.convertAndSend("hello", content);
  }
}