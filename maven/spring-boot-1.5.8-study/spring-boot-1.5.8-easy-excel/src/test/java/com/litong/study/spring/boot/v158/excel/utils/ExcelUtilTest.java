package com.litong.study.spring.boot.v158.excel.utils;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bill robot
 * @date 2020年6月4日_上午8:32:49 
 * @version 1.0 
 * @desc
 */
@Slf4j
public class ExcelUtilTest {
  
  @Test
  public void createFile() {
    File file = new File("1.txt");
    if(!file.exists()) {
      log.info("exists {}",file.exists());
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
      log.info("exists {}",file.exists());
    }
  }

  /**
   * 解析一种奇葩的数excel格式
   */
  @Test
  public void parse() {
    String filePath="2019年模拟数据.xlsx";
    File file = new File(filePath);
    if(!file.exists()) {
      try {
        boolean createNewFile = file.createNewFile();
      } catch (IOException e) {
        log.error("create new file fail");
        e.printStackTrace();
      }
      log.error("file exists {}",file.exists());
      return;
    }
  }

}
