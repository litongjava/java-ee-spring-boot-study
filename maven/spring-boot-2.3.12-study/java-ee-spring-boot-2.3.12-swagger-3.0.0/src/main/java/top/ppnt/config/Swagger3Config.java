package top.ppnt.config;

import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger3Config {
  @Bean
  public Docket createRestApi() {
    // 扫描的包名
    String controllerPackageName = "top.ppnt.controller";
    Predicate<RequestHandler> selector = RequestHandlerSelectors.basePackage(controllerPackageName);
    Predicate<String> any = PathSelectors.any();

    // 建造者模式构建Docket
    Docket docket = new Docket(DocumentationType.OAS_30);
    // 配置builder
    ApiSelectorBuilder apiSelectorBuilder = docket.apiInfo(apiInfo()).groupName("DeepMez").select()
        // 需要放出的接口
        .apis(selector)
        // 过滤
        .paths(any);
    return apiSelectorBuilder.build();
  }

  /**
   * 返回应用信息
   * @return
   */
  private ApiInfo apiInfo() {
    Contact contact = new Contact("ping", "ppnt.top", "litonglinux@qq.com");
    ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
    apiInfoBuilder.title("接口文档").description("描述").contact(contact).version("1.0");
    return apiInfoBuilder.build();
  }
}
