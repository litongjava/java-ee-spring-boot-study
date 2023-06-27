package com.litongjava.spring.boot.minio;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

@SpringBootApplication
public class MinioApplication {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplicationWrapper.run(MinioApplication.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "(ms)");
  }
}