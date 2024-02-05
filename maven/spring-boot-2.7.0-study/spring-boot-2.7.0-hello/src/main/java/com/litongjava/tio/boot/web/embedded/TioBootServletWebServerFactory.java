package com.litongjava.tio.boot.web.embedded;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TioBootServletWebServerFactory extends AbstractServletWebServerFactory
    implements ConfigurableTioBootWebServerFactory, ResourceLoaderAware {
  
  /**
   * org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@557caf28, started on Mon Jan 08 13:55:38 HST 2024
   */
  @Override
  public WebServer getWebServer(ServletContextInitializer... initializers) {
    log.info("{}", initializers);
    return null;
  }

  /**
   * org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext$$Lambda$354/1861616277@3127cb44
   */
  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {
    log.info("{}", resourceLoader);

  }
}
