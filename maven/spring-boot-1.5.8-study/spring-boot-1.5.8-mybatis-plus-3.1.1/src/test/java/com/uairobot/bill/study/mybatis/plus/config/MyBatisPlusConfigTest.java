package com.uairobot.bill.study.mybatis.plus.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ppnt.study.mybatis.plus.model.ImportMp;

/**
 * @author bill robot
 * @date 2020年5月26日_上午11:30:36 
 * @version 1.0 
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ImportMp.class)
public class MyBatisPlusConfigTest {
  
  @Autowired
  private ApplicationContext a;
  
  @Test
  public void getBeans() {
    String[] beanDefinitionNames = a.getBeanDefinitionNames();
    for (String string : beanDefinitionNames) {
      System.out.println(string);
    }
  }

  
}
