package top.ppnt.spring.boot.beel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

import top.ppnt.spring.boot.utils.startup.StartupUtils;

@SpringBootApplication
public class BeelApplication{

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ConfigurableApplicationContext ctx = SpringApplicationWrapper.run(BeelApplication.class, args, true);
    StartupUtils.info(start, ctx);
  }
}