package com.litongjava.easyexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@SuppressWarnings("deprecation")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class BasePurchaseExecutionResponse extends BaseRowModel{
  /**
   * 序号
   */
  @ExcelProperty(value = { "", "", "序号" }, index = 0)
  private String num;
  /**
   * 供应商类型
   */
  @ExcelProperty(value = { "", "", "供应商类型" }, index = 1)
  private String supplierType;
  /**
   * 品牌
   */
  @ExcelProperty(value = { "", "", "品牌" }, index = 2)
  private String brandNameListString;

  /**
   * 年份
   */
  @ExcelProperty(value = { "", "", "年份" }, index = 3)
  private String productYear;
  /**
   * 产品季节
   */
  @ExcelProperty(value = { "", "", "产品季节" }, index = 4)
  private String productSeason;
  /**
   * 总量
   */
  @ExcelProperty(value = { "", "", "总量" }, index = 9)
  private int totalShipment;
  /**
   * 计划交期满足率
   */
  @ExcelProperty(value = { "", "", "计划交期满足率" }, index = 10)
  private String planDeliverRate;
  /**
   * 确认交期满足率
   */
  @ExcelProperty(value = { "", "", "确认交期满足率" }, index = 11)
  private String confirmDeliverRate;

}