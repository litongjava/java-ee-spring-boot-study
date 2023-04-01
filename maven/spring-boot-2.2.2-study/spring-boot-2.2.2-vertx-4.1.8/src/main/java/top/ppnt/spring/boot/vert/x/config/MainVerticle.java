package top.ppnt.spring.boot.vert.x.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.ppnt.spring.boot.vert.x.handler.ConfigHandler;
import top.ppnt.spring.boot.vert.x.handler.FreemarkerHandler;
import top.ppnt.spring.boot.vert.x.handler.FuturePromiseHandler;
import top.ppnt.spring.boot.vert.x.handler.HelloHandler;
import top.ppnt.spring.boot.vert.x.handler.MysqlHandler;
import top.ppnt.spring.boot.vert.x.handler.ThymeleafHandler;

@Component
public class MainVerticle extends BaseVerticle {

  @Autowired
  private ThymeleafHandler thymeleafHandler;
  @Autowired
  private FreemarkerHandler freemarkerHandler;
  @Autowired
  private ConfigHandler configHandler;
  @Autowired
  private MysqlHandler mysqlHandler;
  @Autowired
  private FuturePromiseHandler futurePromiseHandler;

  @Override
  public void start() throws Exception {
    HelloHandler helloHandler = new HelloHandler();
    router.get("/getTest").handler(helloHandler::getTest);
    router.get("/err").handler(helloHandler::err);
    router.get("/thymeleaf").handler(thymeleafHandler::thymeleaf);
    router.get("/freemarker").handler(freemarkerHandler::freemarker);
    router.get("/config").handler(configHandler::getConfig);
    router.get("/configWithFuturePromise").handler(configHandler::getConfigWithFuturePromise);
    
    router.get("/mysql/askLog/list").handler(mysqlHandler::list);
    router.get("/futurePromise/askLog/list").handler(futurePromiseHandler::list);
  }
}