package top.ppnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.core.ContextBase;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class SwaggerApplication {
  public static void main(String[] args) {
    SpringApplication.run(SwaggerApplication.class, args);
  }
}
