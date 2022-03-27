package com.config;

import org.springframework.context.annotation.Import;

@Import({
    // spring-web
    org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration.class,
    org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.class,
    org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration.class,
    org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration.class,
    org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration.class,
    org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration.class,
    org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration.class,
    org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration.class,
    org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.class,
    org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration.class,
    org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration.class,
    org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration.class,
    org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration.class,
    org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration.class,
    org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration.class,
    org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration.class,
    org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration.class,
    org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration.class })
public class SpringWEBAutoConfiguration {

}
