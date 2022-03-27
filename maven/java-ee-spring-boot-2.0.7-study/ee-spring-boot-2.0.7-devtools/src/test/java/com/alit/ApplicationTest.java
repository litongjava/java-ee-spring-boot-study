package com.alit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

  @Autowired
  private ApplicationContext ac;

  @Test
  public void test() {
    int i = 0;
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for (String b : beanDefinitionNames) {
      System.out.println(++i + ":" + b);
    }
  }

}
