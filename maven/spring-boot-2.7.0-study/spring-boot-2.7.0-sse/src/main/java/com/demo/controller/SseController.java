package com.demo.controller;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

@RestController
public class SseController {

  private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/events")
  public SseEmitter handleSSE() {
    SseEmitter emitter = new SseEmitter();

    // TODO: 在此添加事件处理逻辑

    return emitter;
  }

  @GetMapping("/checkServer")
  public ResponseEntity<String> checkServer() {
    // TODO: 检查服务器是否可用，返回相应的响应状态
    return ResponseEntity.ok("Server is available");
  }

  @GetMapping("/sse")
  public SseEmitter handleSse() {
    SseEmitter emitter = new SseEmitter();
    emitters.add(emitter);

    emitter.onCompletion(() -> emitters.remove(emitter));

    new Thread(() -> {
      try {
        for (int i = 0; i < 10; i++) {
          String id = String.valueOf(counter.incrementAndGet());
          String eventName = "message";
          String data = "This is message " + i;
          SseEventBuilder builder = SseEmitter.event().id(id).name(eventName).data(data);

          emitter.send(builder);
          Thread.sleep(1000);
        }
        emitter.complete();
      } catch (IOException | InterruptedException e) {
        emitter.completeWithError(e);
      }
    }).start();

    return emitter;
  }

}
