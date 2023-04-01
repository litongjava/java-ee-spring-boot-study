package top.ppnt.study.spring.boot.elasticsearch.demo01;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

import top.ppnt.spring.boot.utils.startup.StartupUtils;

@SpringBootApplication
public class ElasticSearchDemo01Application {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ConfigurableApplicationContext ctx = SpringApplicationWrapper.run(ElasticSearchDemo01Application.class, args);
    StartupUtils.info(start, ctx);
  }
}