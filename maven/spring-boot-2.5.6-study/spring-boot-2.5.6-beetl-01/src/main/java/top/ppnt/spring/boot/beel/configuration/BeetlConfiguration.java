package top.ppnt.spring.boot.beel.configuration;

import java.io.IOException;

import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

@Configuration
public class BeetlConfiguration {
  @Bean(initMethod = "init", name = "beetlConfig")
  public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
    BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();

    ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
    Resource resource = patternResolver.getResource("classpath:/");

    try {
      // WebAppResourceLoader 配置root路径是关键
      String path = resource.getFile().getPath();
      WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(path);
      beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 读取配置文件信息
    return beetlGroupUtilConfiguration;
  }

  @Bean(name = "beetlViewResolver")
  public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
    BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
    beetlSpringViewResolver.setPrefix("WEB-INF/views/");
    beetlSpringViewResolver.setSuffix(".html");
    beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
    beetlSpringViewResolver.setOrder(0);
    beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
    return beetlSpringViewResolver;
  }

}
