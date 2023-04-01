package com.litongjava.spring.boot.mybatis.plus.h2.dao;

import com.alibaba.fastjson.JSON;
import com.litongjava.spring.boot.mybatis.plus.h2.ImprotMp;
import com.litongjava.spring.boot.mybatis.plus.h2.dao.GooldsDetailDao;
import com.litongjava.spring.boot.mybatis.plus.h2.model.GooldsDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author litongjava@qq.com on 2023/2/1 23:33
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprotMp.class)
public class GooldsDetailDaoTest {

  @Autowired
  private GooldsDetailDao gooldsDetailDao;

  @Test
  public void testSaveList() {
    GooldsDetail entity = new GooldsDetail(3L, Arrays.asList(1, 2));
    int insert = gooldsDetailDao.insert(entity);
    GooldsDetail result = gooldsDetailDao.selectById(3L);
    System.out.println(JSON.toJSONString(result));
  }

}