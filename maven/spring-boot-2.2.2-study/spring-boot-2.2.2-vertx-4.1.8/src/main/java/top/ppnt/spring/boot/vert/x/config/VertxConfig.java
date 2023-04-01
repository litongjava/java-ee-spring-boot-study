package top.ppnt.spring.boot.vert.x.config;

import java.util.Map;
import java.util.function.BiConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSONObject;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;
import lombok.extern.slf4j.Slf4j;
import top.ppnt.spring.boot.vert.x.vo.ResultDTO;

@Slf4j
@Configuration
public class VertxConfig {

  @Autowired
  Environment environment;

  /**
   * vertx
   * @return
   */
  @Bean
  public Vertx vertx() {
    VertxOptions options = new VertxOptions().setEventLoopPoolSize(20).setWorkerPoolSize(20);
    return Vertx.vertx(options);
  }

  /**
   * router
   * @param vertx
   * @return
   */
  @Bean
  public Router router(Vertx vertx) {
    Router router = Router.router(vertx);
    globalIntercept(router);
    globalError(router);
    cors(router);
    return router;
  }

  /**
   * httpServer
   * @param vertx
   * @param router
   * @param applicationContext
   * @return
   */
  @Bean
  public HttpServer httpServer(Vertx vertx, Router router, ApplicationContext applicationContext) {

    // 发布verticle
    BiConsumer<? super String, ? super AbstractVerticle> action = (k, verticle) -> {
      vertx.deployVerticle(verticle);
    };
    // 获取所有子类
    Map<String, AbstractVerticle> beansOfType = applicationContext.getBeansOfType(AbstractVerticle.class);
    beansOfType.forEach(action);

    Handler<AsyncResult<HttpServer>> listenHandler = res -> {
      if (res.succeeded()) {
        log.info("vert.x success to listen： " + getPort());
      } else {
        log.info("vert.x fail:" + res.cause().getMessage());
      }
    };
    HttpServer httpServer = vertx.createHttpServer();
    httpServer.requestHandler(router).listen(getPort(), "0.0.0.0", listenHandler);
    return httpServer;
  }

  // 跨域处理
  private void cors(Router router) {
    CorsHandler corsHandler = CorsHandler.create();
    corsHandler
        //
        .addOrigin("*")
        //
        .allowedHeader(" x-www-form-urlencoded, Content-Type,x-requested-with")
        //
        .allowedMethod(HttpMethod.GET).allowedMethod(HttpMethod.POST)
        //
        .allowedMethod(HttpMethod.PUT).allowedMethod(HttpMethod.DELETE);
    // route
    Route route = router.route();
    // handler
    route.handler(corsHandler);
  }

  // 全局异常返回
  private void globalError(Router router) {
    // failureHandler
    Handler<RoutingContext> failureHandler = ctx -> {
      // 获取错误信息
      Throwable failure = ctx.failure();
      log.info("输出异常信息:{}",failure.getMessage());
      ResultDTO resultDto = ResultDTO.err(failure.getMessage());
      
      // 转为json格式
      String jsonString = JSONObject.toJSONString(resultDto);
      // 返回
      ctx.response().end(jsonString);
    };

    Route route = router.route();
    // 设置错误处理器
    route.failureHandler(failureHandler);
  }

  // 全局拦截器
  private void globalIntercept(Router router) {
    // 实例化全局处理器
    Handler<RoutingContext> requestHandler = ctx -> {
      ctx.response().putHeader("Content-Type", "application/json");
      ctx.next();
    };
    // 获取全局请求
    Route route = router.route("/*");
    // 设置全局处理器
    route.handler(requestHandler);
  }

  /**
   * 获取端口
   * @return
   */
  public Integer getPort() {
    return environment.getProperty("server.port", Integer.class, 8080);
  }
}
