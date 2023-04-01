package top.ppnt.spring.boot.vert.x.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

@Component
public abstract class BaseVerticle extends AbstractVerticle {
  @Autowired
  public Router router;
}