package top.ppnt.actable;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

import top.ppnt.spring.boot.utils.startup.StartupUtils;

@SpringBootApplication
@MapperScan({ "top.ppnt.actable.mapper", "com.gitee.sunchenbin.mybatis.actable.dao.*" })
@ComponentScan("com.gitee.sunchenbin.mybatis.actable.manager.*")
public class AcTableApplicaton {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ConfigurableApplicationContext ctx = SpringApplicationWrapper.run(AcTableApplicaton.class, args, true);
    StartupUtils.info(start, ctx);
  }
}