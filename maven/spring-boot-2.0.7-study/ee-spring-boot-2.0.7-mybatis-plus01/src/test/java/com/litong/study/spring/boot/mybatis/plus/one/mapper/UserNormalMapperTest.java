package com.litong.study.spring.boot.mybatis.plus.one.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.litong.study.spring.boot.mybatis.plus.one.AppOnlyDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppOnlyDao.class)
public class UserNormalMapperTest {

  @Autowired
  private UserNormalMapper userNormalMapper;
  @Autowired
  private ApplicationContext applicationContext;

  @Test
  public void test() {
    try {
      List<String> name = userNormalMapper.getName("李通");
      System.out.println(name);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getBean() {
    String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
    for (String string : beanDefinitionNames) {
      System.out.println(string);
    }
  }
}
