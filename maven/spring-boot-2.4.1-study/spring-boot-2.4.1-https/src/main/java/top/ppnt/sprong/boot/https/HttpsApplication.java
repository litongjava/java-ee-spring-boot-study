package top.ppnt.sprong.boot.https;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HttpsApplication {

  @Value("${http.port}")
  private Integer port;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(HttpsApplication.class, args);
  }

  // 这是spring boot 2.0.X版本的 添加这个，上一个就不用添加了
  @Bean
  public ServletWebServerFactory servletContainer() {
    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
    // 添加Http
    tomcat.addAdditionalTomcatConnectors(createStandardConnector());
    return tomcat;
  }

  // 配置http
  private Connector createStandardConnector() {
    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    connector.setPort(port);
    return connector;
  }

}