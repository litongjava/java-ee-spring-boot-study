package com.litongjava.easyexcel.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.litongjava.easyexcel.pojo.Student;
import com.litongjava.utils.excel.ExcelUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author create by Ping E Lee on 2022年3月25日 上午9:14:44
 *
 */
@Controller
@RequestMapping("excel")
public class ExcelController {

  @RequestMapping("export")
  public void export(HttpServletResponse response) {
    // 模拟用户查询数据
    List<Student> data = new ArrayList<>();
    data.add(new Student(1, "张1", 11.1, new Date()));
    data.add(new Student(2, "张2", 11.2, new Date()));
    data.add(new Student(3, "张3", 11.3, new Date()));
    data.add(new Student(4, "张4", 11.4, new Date()));
    data.add(new Student(5, "张5", 11.5, new Date()));

    String filename = "用户管理";
    String sheetName = "用户管理";
    ExcelUtils.export(response, filename, sheetName, data, Student.class);
  }
}
