package com;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.litongjava.hotswap.wrapper.forkapp.ForkApp;

@SpringBootApplication
public class HelloApp {

  public static void main(String[] args) {
    ForkApp.run(() -> {
      SpringApplication.run(HelloApp.class, args);
    });

  }
}
