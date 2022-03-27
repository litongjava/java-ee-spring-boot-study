package com.litongjava.easyexcel.pojo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@HeadRowHeight(value = 35) // 表头行高
@ContentRowHeight(value = 25) // 内容行高
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

  @ExcelProperty(value = { "学生信息", "学生编号" }, order = 10)
  private Integer id;

  @ExcelProperty(value = { "学生信息", "学生姓名" }, order = 2)
  private String name;

  @ExcelProperty(value = { "学生信息", "学生薪水" }, order = 1)
  private Double salary;

  @ExcelProperty(value = { "学生信息", "学生生日" }, order = 11)
  private Date birthday;
}
