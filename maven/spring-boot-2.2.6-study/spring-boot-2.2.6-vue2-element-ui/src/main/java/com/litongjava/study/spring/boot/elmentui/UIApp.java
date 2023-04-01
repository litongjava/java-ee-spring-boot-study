package com.litongjava.study.spring.boot.elmentui;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

@SpringBootApplication
public class UIApp {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplicationWrapper.run(UIApp.class, args);
    long end = System.currentTimeMillis();
    System.err.println((end - start) + "(ms)");

  }
}