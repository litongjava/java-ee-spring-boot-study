package com.alit.study.springboot.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alit.study.springboot.rabbitmq.component.Sender;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

  @Autowired
  private Sender sender;

  @Test
  public void test() {
    sender.send();
  }
}
