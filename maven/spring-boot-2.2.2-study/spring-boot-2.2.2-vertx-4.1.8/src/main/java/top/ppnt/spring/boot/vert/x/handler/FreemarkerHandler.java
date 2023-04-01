package top.ppnt.spring.boot.vert.x.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;

/**
 * @author Ping E Lee
 *
 */
@Component
public class FreemarkerHandler {

  @Autowired
  private FreeMarkerTemplateEngine freeMarkerTemplateEngine;

  public void freemarker(RoutingContext ctx) {

    List<String> list = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      list.add("序号：" + i);
    }

    // data
    JsonObject data = new JsonObject().put("name", "Ping").put("age", "18").put("list", list);
    // template
    String templateFileName = "templates/index.ftl";
    // handler
    Handler<AsyncResult<Buffer>> handler = res -> {
      if (res.succeeded()) {
        ctx.response().putHeader("Content-Type", "text/html;charset=UTF-8").end(res.result());
      } else {
        res.cause().printStackTrace();
        ctx.fail(res.cause());
      }
    };

    freeMarkerTemplateEngine.render(data, templateFileName, handler);
  }
}
