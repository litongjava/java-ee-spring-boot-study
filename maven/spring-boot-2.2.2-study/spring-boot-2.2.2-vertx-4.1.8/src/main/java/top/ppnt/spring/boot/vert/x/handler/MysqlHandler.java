package top.ppnt.spring.boot.vert.x.handler;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vertx.config.ConfigRetriever;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlConnection;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Ping E Lee
 *
 */
@Component
@Slf4j
public class MysqlHandler {

  @Autowired
  private ConfigRetriever configRetriever;
  @Autowired
  private Vertx vertx;

  public void list(RoutingContext routingContext) {
    HttpServerResponse response = routingContext.response();
    // 拼接sql
    String tableName = "om_log_ask_detail_202008";
    String sqlTemplate = "select * from %s limit 1,10";
    // 实际执行的sql
    String finalSql = String.format(sqlTemplate, tableName);
    log.info("执行的sql:{}",finalSql);

    Handler<AsyncResult<SqlConnection>> connectionHandler = asyncResult -> {
      // 连接获取成功后在进行操作
      if (asyncResult.succeeded()) {
        log.info("connect succeeded");
        // 获取连接结果
        SqlConnection connection = asyncResult.result();

        Handler<AsyncResult<RowSet<Row>>> queryHandler = ar2 -> {
          if (ar2.succeeded()) {
            // 获取查询返回对象
            RowSet<Row> result = ar2.result();
            var list = new ArrayList<JsonObject>();
            result.forEach(item -> {
              var json = new JsonObject();
              json.put("id", item.getValue("ID"));
              json.put("question", item.getValue("QUESTION"));
              list.add(json);
            });
            response.putHeader("content-type", "application/json");
            response.end(list.toString());
          } else {
            // 关闭连接
            connection.close();
            // 输出连接失败信息
            response.end(ar2.cause().getMessage());
          }
        };
        
        // 执行查询
        connection.query(finalSql).execute(queryHandler);
      } else {
        // 输出连接失败信息
        System.out.println("could not get connection:" + asyncResult.cause().getMessage());
      }
    };

    // 读取配文件完成
    Handler<AsyncResult<JsonObject>> completionHandler = ar -> {
      if (ar.failed()) {
        log.info("读取配置文件失败");
      } else {
        // 获取mysql地址信息
        MySQLPool mySQLPool = getMysqlPool(ar);
        mySQLPool.getConnection(connectionHandler);
      }
    };

    configRetriever.getConfig(completionHandler);
  }

  /**
   * 获取mysql连接池
   * @param ar
   * @return
   */
  public MySQLPool getMysqlPool(AsyncResult<JsonObject> ar) {
    JsonObject config = ar.result();
    String host = config.getString("host");
    int port = Integer.parseInt(config.getValue("port").toString());
    String user = config.getString("user");
    String password = config.getString("password");
    String database = config.getString("database");
    log.info("host:{},port:{},user:{},password:{},database:{}", host, port, user, password, database);
    // mysql 连接信息
    MySQLConnectOptions mySQLConnectOptions = new MySQLConnectOptions();
    mySQLConnectOptions.setHost(host).setPort(port).setUser(user).setPassword(password).setDatabase(database);

    // 连接池信息
    PoolOptions poolOptions = new PoolOptions().setMaxSize(5);
    MySQLPool mySQLPool = MySQLPool.pool(vertx, mySQLConnectOptions, poolOptions);
    return mySQLPool;
  }
}
