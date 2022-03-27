package com.litong.study.spring.boot.v158.excel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 表格实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WaterLevelRowModel extends BaseRowModel {

  @ExcelProperty(index = 0, value = "年")
  private String column0;
  @ExcelProperty(index = 1, value = "月")
  private String column1;

  @ExcelProperty(index = 2, value = "日")
  private String column2;

  @ExcelProperty(index = 3, value = "宜宾")
  private String column3;

  @ExcelProperty(index = 4, value = "泸 州：二郎滩 ")
  private String column4;

  @ExcelProperty(index = 5, value = "重庆")
  private String column5;

  @ExcelProperty(index = 6, value = "北碚")
  private String column6;

  @ExcelProperty(index = 7, value = "涪  陵：清溪场")
  private String column7;

  @ExcelProperty(index = 8, value = "万  县：吴淞高程")
  private String column8;

  @ExcelProperty(index = 9, value = "茅 坪")
  private String column9;

  @ExcelProperty(index = 10, value = "宜昌")
  private String column10;

  @ExcelProperty(index = 11, value = "沙市")
  private String column11;

  @ExcelProperty(index = 12, value = "监利")
  private String column12;

  @ExcelProperty(index = 13, value = "城陵矶")
  private String column13;

  @ExcelProperty(index = 14, value = "汉口")
  private String column14;

  @ExcelProperty(index = 15, value = "黄石")
  private String column15;

  @ExcelProperty(index = 16, value = "九江")
  private String column16;

  @ExcelProperty(index = 17, value = "安庆")
  private String column17;

  @ExcelProperty(index = 18, value = "芜湖")
  private String column18;

  @ExcelProperty(index = 19, value = "南京")
  private String column19;

  @ExcelProperty(index = 20, value = "镇江")
  private String column20;

  @ExcelProperty(index = 21, value = "长沙")
  private String column21;
  
  @ExcelProperty(index = 22, value = "枝江")
  private String column22;


}