package com.litongjava.spring.boot.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket productApi() {
    Predicate<RequestHandler> apis = RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class);
    Predicate<String> paths = PathSelectors.any();
    Docket docket = new Docket(DocumentationType.SWAGGER_2);
    ApiSelectorBuilder builder = docket.apiInfo(apiInfo()).select();
    return builder.apis(apis) // 添加ApiOperiation注解的被扫描
        .paths(paths).build();

  }

  private ApiInfo apiInfo() {
    ApiInfoBuilder builder = new ApiInfoBuilder();
    return builder.title("swagger和springBoot整合").description("swagger的API文档").version("1.0").build();

  }

}