package com.litong.spring.boot.v207.web.hello;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.*;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import({
  PropertyPlaceholderAutoConfiguration.class,
  WebSocketServletAutoConfiguration.class,
  ServletWebServerFactoryAutoConfiguration.class,
  DispatcherServletAutoConfiguration.class,
  ValidationAutoConfiguration.class,
  ErrorMvcAutoConfiguration.class,
  WebMvcAutoConfiguration.class,
  JmxAutoConfiguration.class,
  SpringApplicationAdminJmxAutoConfiguration.class,
  ConfigurationPropertiesAutoConfiguration.class,
  JacksonAutoConfiguration.class,
  HttpMessageConvertersAutoConfiguration.class,
  CodecsAutoConfiguration.class,
  ProjectInfoAutoConfiguration.class,
  RestTemplateAutoConfiguration.class,
  EmbeddedWebServerFactoryCustomizerAutoConfiguration.class,
  HttpEncodingAutoConfiguration.class,
  MultipartAutoConfiguration.class
})
public class ImportAutoConfiguration {
}
