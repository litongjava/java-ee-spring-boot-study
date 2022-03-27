package com.litongjava.spring.boot.utils;

import com.litongjava.spring.boot.SpiderApplication;
import com.litongjava.spring.boot.services.SmzdmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by litonglinux@qq.com on 2022/1/20_13:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpiderApplication.class)
public class SpringContextUtilsTest {

  @Test
  public void getBean(){
    SmzdmService bean = SpringContextUtils.getBean(SmzdmService.class);
    System.out.println(bean);
  }

}