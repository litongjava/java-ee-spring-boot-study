package top.ppnt.spring.boot.vert.x.handler;

import com.alibaba.fastjson.JSONObject;

import io.vertx.ext.web.RoutingContext;
import top.ppnt.spring.boot.vert.x.vo.ResultDTO;

/**
 * @author Ping E Lee
 *
 */
public class HelloHandler {

  public void getTest(RoutingContext ctx) {
    ctx.response().end(JSONObject.toJSONString(ResultDTO.ok()));
  }

  public void err(RoutingContext ctx) {
    int a = 1 / 0;
  }
}
