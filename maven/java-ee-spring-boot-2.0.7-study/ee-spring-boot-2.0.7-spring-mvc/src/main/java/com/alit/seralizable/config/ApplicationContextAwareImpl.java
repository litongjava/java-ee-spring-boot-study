package com.alit.seralizable.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextAwareImpl implements ApplicationContextAware {
  private static ApplicationContext context = null;
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
  }
  public static ApplicationContext getApplicationContext() {
    return context;
  }
  public synchronized static Object getBean(String beanName) {
    return context.getBean(beanName);
  }
  public static <T> T getBean(Class<T> clazz) {
    return context.getBean(clazz);
  }
}