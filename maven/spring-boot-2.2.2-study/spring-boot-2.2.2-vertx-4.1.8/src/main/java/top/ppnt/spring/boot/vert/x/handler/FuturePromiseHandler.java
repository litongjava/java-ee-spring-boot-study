package top.ppnt.spring.boot.vert.x.handler;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vertx.config.ConfigRetriever;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
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

@Component
@Slf4j
public class FuturePromiseHandler {
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
    log.info("执行的sql:{}", finalSql);

    Future<JsonObject> futureJsonObject = getConfigJsonObject(configRetriever);

    Future<SqlConnection> futureSqlConnection = futureJsonObject.compose(JsonObject -> {
      MySQLPool mySQLPool = getMysqlPool(JsonObject);
      Future<SqlConnection> connection = getConnection(mySQLPool);
      return connection;
    });

    Future<RowSet<Row>> futureRows = futureSqlConnection.compose(connection -> {
      Future<RowSet<Row>> rowSet = this.getRows(connection, finalSql);
      return rowSet;
    });

    Handler<RowSet<Row>> sucessHandler = rows -> {
      // 第三步 加工查询出来的数据，并返回给客户端
      var list = new ArrayList<JsonObject>();
      rows.forEach(item -> {
        var json = new JsonObject();
        json.put("id", item.getValue("ID"));
        json.put("question", item.getValue("QUESTION"));
        list.add(json);
      });
      response.putHeader("content-type", "application/json").end(list.toString());
    };

    futureRows.onSuccess(sucessHandler);

  }

  /**
   * 获取mysql连接池
   * @param ar
   * @return
   */
  public MySQLPool getMysqlPool(JsonObject jsonObject) {
    String host = jsonObject.getString("host");
    int port = Integer.parseInt(jsonObject.getValue("port").toString());
    String user = jsonObject.getString("user");
    String password = jsonObject.getString("password");
    String database = jsonObject.getString("database");
    log.info("host:{},port:{},user:{},password:{},database:{}", host, port, user, password, database);
    // mysql 连接信息
    MySQLConnectOptions mySQLConnectOptions = new MySQLConnectOptions();
    mySQLConnectOptions.setHost(host).setPort(port).setUser(user).setPassword(password).setDatabase(database);

    // 连接池信息
    PoolOptions poolOptions = new PoolOptions().setMaxSize(5);
    MySQLPool mySQLPool = MySQLPool.pool(vertx, mySQLConnectOptions, poolOptions);
    return mySQLPool;
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

  private Future<SqlConnection> getConnection(MySQLPool mySQLPool) {
    Promise<SqlConnection> promise = Promise.promise();
    mySQLPool.getConnection(ar1 -> {
      if (ar1.succeeded()) {
        log.info("Connected");
        SqlConnection conn = ar1.result();
        promise.complete(conn);
      } else {
        promise.fail(ar1.cause());
      }
    });
    return promise.future();
  }

  private Future<RowSet<Row>> getRows(SqlConnection connection, String sql) {
    Promise<RowSet<Row>> promise = Promise.promise();
    connection.query(sql).execute(ar2 -> {
      connection.close();
      if (ar2.succeeded()) {
        RowSet<Row> result = ar2.result();
        promise.complete(result);
      } else {
        promise.fail(ar2.cause());
      }
    });
    return promise.future();
  }
}
