package com.litongjava.logbak.stomp.config;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import com.litongjava.logbak.stomp.queue.LoggerQueue;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // setAllowedOrigins设置的允许连接的源地址
    registry.addEndpoint("/websocket").setAllowedOrigins("*").withSockJS();
  }

  /**
   * 推送日志到/topic/pullLogger
   */
  @PostConstruct
  public void pushLogger() {
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    Runnable runnable = () -> {
      while (true) {
        try {
          String message = LoggerQueue.getInstance().poll();
          if (message != null) {
            if (messagingTemplate != null)
              messagingTemplate.convertAndSend("/topic/pullLogger", message);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };
    executorService.submit(runnable);
  }
}