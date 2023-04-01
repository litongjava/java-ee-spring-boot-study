package com.ppnt.study.mybatis.plus.mapper;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ppnt.study.mybatis.plus.ImportMyBatisPlus;
import com.ppnt.study.mybatis.plus.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImportMyBatisPlus.class)
public class UserMapperTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void testInsert() {
    User user = new User();
    user.setName("litong");
    user.setAge(18);
    user.setEmail("litongjava@qq.com");
    user.setAddr("广东深圳龙岗");
    user.setRemark("喝酱油的");
    int insert = userMapper.insert(user);
    Assert.assertEquals(insert, 1);

  }

  @Test
  public void testSelect() {
    List<User> users = userMapper.selectList(null);
    users.forEach(System.out::println);
    Assert.assertEquals(2, users.size());
  }
}