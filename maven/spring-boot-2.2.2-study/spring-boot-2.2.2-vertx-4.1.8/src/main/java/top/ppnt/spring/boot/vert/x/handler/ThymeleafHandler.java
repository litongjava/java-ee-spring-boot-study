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
import io.vertx.ext.web.templ.thymeleaf.ThymeleafTemplateEngine;

/**
 * @author Ping E Lee
 *
 */
@Component
public class ThymeleafHandler {
  @Autowired
  public ThymeleafTemplateEngine thymeleafTemplateEngine;

  public void thymeleaf(RoutingContext ctx) {
    List<String> list = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      list.add("序号：" + i);
    }

    // 数据
    JsonObject data = new JsonObject().put("name", "小毕超").put("age", "18").put("list", list);
    // 模板文件名称
    String templateFileName = "templates/index.html";
    // 处理器
    Handler<AsyncResult<Buffer>> handler = res -> {
      if (res.succeeded()) {
        ctx.response().putHeader("Content-Type", "text/html; charset=UTF-8").end(res.result());
      } else {
        ctx.fail(res.cause());
      }
    };
    thymeleafTemplateEngine.render(data, templateFileName, handler);
  }

}
