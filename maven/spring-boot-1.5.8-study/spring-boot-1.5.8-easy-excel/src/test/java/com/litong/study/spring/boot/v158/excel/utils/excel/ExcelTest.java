package com.litong.study.spring.boot.v158.excel.utils.excel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.litong.study.spring.boot.v158.excel.entity.WaterLevelRowModel;

public class ExcelTest {
  /**
   * 简单读取 (同步读取)
   */
  @Test
  public void simpleReadExcel() {
    // 读取 excel 表格的路径
    String readPath = "模拟数据.xls";
    Sheet sheet = new Sheet(1, 1, WaterLevelRowModel.class);
    List<Object> readList = null;
    try {
      // 这里 取出来的是 ExcelModel实体 的集合
      readList = EasyExcelFactory.read(new BufferedInputStream(new FileInputStream(readPath)), sheet);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    // 输出数据
    readList.forEach(i -> {
      System.out.println(i);
    });
  }

  // 异步读取
  @Test
  public void simpleReadExcel2() {
    // 读取 excel 表格的路径
    String readPath = "模拟数据.xls";
    Sheet sheet = new Sheet(1, 1, WaterLevelRowModel.class);
    try {
      EasyExcelFactory.readBySax(new BufferedInputStream(new FileInputStream(readPath)), sheet, new ExcelModelListener());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
