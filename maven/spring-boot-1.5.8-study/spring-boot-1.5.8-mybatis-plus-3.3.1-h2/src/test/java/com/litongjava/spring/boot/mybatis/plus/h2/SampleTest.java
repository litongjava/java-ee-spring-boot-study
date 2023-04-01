package com.litongjava.spring.boot.mybatis.plus.h2;

import com.litongjava.spring.boot.mybatis.plus.h2.dao.UserDao;
import com.litongjava.spring.boot.mybatis.plus.h2.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprotMp.class)
public class SampleTest {

  @Autowired
  private UserDao userDao;

  @Test
  public void testSelect() {
    System.out.println(("----- selectAll method test ------"));
    List<User> userList = userDao.selectList(null);
    Assert.assertEquals(5, userList.size());
    userList.forEach(System.out::println);
  }
}