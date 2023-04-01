package top.ppnt.spring.boot.vert.x.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.vertx.config.ConfigRetriever;
import io.vertx.core.Vertx;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;
import io.vertx.ext.web.templ.thymeleaf.ThymeleafTemplateEngine;

@Configuration
public class BeanConfig {

  @Bean
  public ThymeleafTemplateEngine thymeleafTemplateEngine(Vertx vertx) {
    return ThymeleafTemplateEngine.create(vertx);
  }

  @Bean
  public FreeMarkerTemplateEngine freeMarkerTemplateEngine(Vertx vertx) {
    return FreeMarkerTemplateEngine.create(vertx);
  }

  @Bean
  public ConfigRetriever configRetriever(Vertx vertx) {
    ConfigRetriever configRetriever = ConfigRetriever.create(vertx);
    return configRetriever;
  }

}
