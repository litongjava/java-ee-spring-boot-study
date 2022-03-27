package io.jonashackt.springbootgraal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author litong
 * @date 2020年9月17日_下午12:58:27 
 * @version 1.0 
 * @desc
 */
@SpringBootApplication(proxyBeanMethods = false)
public class SpringBootHelloApplication {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(SpringBootHelloApplication.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
