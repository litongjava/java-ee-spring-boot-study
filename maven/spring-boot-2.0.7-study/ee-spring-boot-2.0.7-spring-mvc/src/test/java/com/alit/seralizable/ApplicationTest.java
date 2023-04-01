package com.alit.seralizable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mvc.xml")
public class ApplicationTest {

  @Autowired
  private ApplicationContext ac;

  @Test
  public void getBean() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for (String string : beanDefinitionNames) {
      System.out.println(string);
    }
  }
}
