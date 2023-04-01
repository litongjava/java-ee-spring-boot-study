package top.ppnt.spring.boot.vert.x;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

@SpringBootApplication
public class VertxApplication {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    // SpringApplication.run(VertxApplication.class, args);
    SpringApplicationWrapper.run(VertxApplication.class, args, true);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}