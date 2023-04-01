package com.litong.study.spring.boot.v158.excel.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.litong.study.spring.boot.v158.excel.ImportMp;
import com.litong.study.spring.boot.v158.excel.entity.WaterLevel;

/**
 * @author bill robot
 * @date 2020年6月5日_下午10:25:08 
 * @version 1.0 
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ImportMp.class)
public class WaterLevelServiceTest {

  @Autowired
  private WaterLevelService wls;

  @Test
  public void testQueryWrapperEntity() {
    WaterLevel entity = new WaterLevel();
    entity.setSiteName("宜昌");
    entity.setTime("2019-01-01");
    QueryWrapper<WaterLevel> queryWrapper = new QueryWrapper<WaterLevel>(entity);
    WaterLevel one = wls.getOne(queryWrapper);
    System.out.println(one);
  }

  @Test
  public void updateByWrapper() {
    // 查询条件
    WaterLevel oldEntity = new WaterLevel();
    oldEntity.setSiteName("宜宾");
    oldEntity.setTime("2019-01-01");
    Wrapper<WaterLevel> updateWrapper = new UpdateWrapper<>(oldEntity);
    // 保存数据
    WaterLevel newEntity = new WaterLevel();
    newEntity.setLevel("10");
    wls.update(newEntity, updateWrapper);
  }
}
