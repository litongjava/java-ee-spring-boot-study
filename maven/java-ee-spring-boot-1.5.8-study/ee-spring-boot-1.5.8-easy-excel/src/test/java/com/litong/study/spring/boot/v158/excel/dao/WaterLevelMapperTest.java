package com.litong.study.spring.boot.v158.excel.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.litong.study.spring.boot.v158.excel.ImportMp;
import com.litong.study.spring.boot.v158.excel.entity.WaterLevel;
import com.litong.study.spring.boot.v158.excel.entity.WaterLevelRowModel;
import com.litong.study.spring.boot.v158.excel.service.WaterLevelService;
import com.litong.study.spring.boot.v158.excel.utils.excel.WaterLevelImportListener;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bill robot
 * @date 2020年6月4日_上午8:24:09 
 * @version 1.0 
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ImportMp.class)
@Slf4j
public class WaterLevelMapperTest {

  @Autowired
  private WaterLevelService wls;
  @Autowired
  private WaterLevelMapper wlm;
  @Autowired
  ApplicationContext applicationContext;

  @Test
  public void getBeans() {
    String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
    for (int i = 0; i < beanDefinitionNames.length; i++) {
      System.out.println(i + 1 + ":" + beanDefinitionNames[i]);
    }
  }

  @Test
  public void list() {
    List<WaterLevel> selectList = wlm.selectList(null);
    System.out.println(selectList.size());
  }

  @Test
  public void readExcelNoClass() {
    String filePath = "2019年模拟数据.xls";
    Sheet sheet = new Sheet(1, 0);
    List<Object> readList = null;
    try {
      // 这里 取出来的是 ExcelModel实体 的集合
      readList = EasyExcelFactory.read(new BufferedInputStream(new FileInputStream(filePath)), sheet);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(readList);
  }

  @Test
  public void readExcelToList() {
    String filePath = "2020年模拟数据.xlsx";
    Sheet sheet = new Sheet(1, 1, WaterLevelRowModel.class);
    // Sheet sheet = new Sheet(1, 1, ArrayList.class);
    List<Object> readList = null;
    try {

      // 这里 取出来的是 ExcelModel实体 的集合
      readList = EasyExcelFactory.read(new BufferedInputStream(new FileInputStream(filePath)), sheet);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * 解析一种奇葩的excel格式,导入的数据库中
   */
  @Test
  public void importDB() {
    long start = System.currentTimeMillis();

//    String filePath = "2017年水位数据.xlsx";
//    String filePath="2018年水位数据.xls";
//    String filePath = "2019年水位数据.xls";
//    String filePath="2020年水位数据.xls";
    String filePath = "2019-09水位数据.xls";
    File file = new File(filePath);
    if (!file.exists()) {
      log.error("file exists {}", filePath);
      return;
    }
    Sheet sheet = new Sheet(1, 0);
    try {
      // 这里 取出来的是 ExcelModel实体 的集合
      WaterLevelImportListener listener = new WaterLevelImportListener(wls);
      EasyExcelFactory.readBySax(new BufferedInputStream(new FileInputStream(filePath)), sheet, listener);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
