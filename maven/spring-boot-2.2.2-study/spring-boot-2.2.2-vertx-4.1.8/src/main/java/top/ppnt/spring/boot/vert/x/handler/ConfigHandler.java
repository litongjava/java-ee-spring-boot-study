package top.ppnt.spring.boot.vert.x.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vertx.config.ConfigRetriever;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Ping E Lee
 *
 */
@Component
@Slf4j
public class ConfigHandler {

  @Autowired
  private ConfigRetriever configRetriever;

  public void getConfig(RoutingContext routingContext) {
    HttpServerResponse response = routingContext.response();

    Handler<AsyncResult<JsonObject>> completionHandler = ar -> {
      if (ar.succeeded()) {
        JsonObject result = ar.result();
        String string = result.toString();
        log.info("string:{}", string);
        response.putHeader("Content-type", "application/json").end(string);
      }
    };
    configRetriever.getConfig(completionHandler);
  }

  public void getConfigWithFuturePromise(RoutingContext routingContext) {
    //获取response
    HttpServerResponse response = routingContext.response();
    //获取future
    Future<JsonObject> futureJsonObject = getConfigJsonObject(configRetriever);
    //onSuccess
    futureJsonObject.onSuccess(JsonObject -> {
      String string = JsonObject.toString();
      log.info("string:{}", string);
      response.putHeader("Content-type", "application/json").end(string);
    });
  }

  private Future<JsonObject> getConfigJsonObject(ConfigRetriever configRetriever) {
    // 获取promise 这个写法是固定写法
    Promise<JsonObject> promise = Promise.promise();

    configRetriever.getConfig(ar1 -> {
      if (ar1.succeeded()) {
        // Obtain our connection
        JsonObject result = ar1.result();
        // 成功
        promise.complete(result);
      } else {
        // 失败
        promise.fail(ar1.cause());
      }
    });

    // pormise转为Future
    return promise.future();
  }
}
