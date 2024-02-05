package demo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;
import com.litongjava.jfinal.aop.annotation.AComponentScan;
import com.litongjava.tio.boot.spring.SpringBootArgs;
import com.litongjava.tio.boot.spring.TioBootServerAutoConfiguration;

@SpringBootApplication
@Import({TioBootServerAutoConfiguration.class})
@AComponentScan
public class SpringWebFluxTioBootApp {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringBootArgs.set(SpringWebFluxTioBootApp.class, args);
    SpringApplicationWrapper.run(SpringWebFluxTioBootApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println(end - start + "(ms)");

  }
}