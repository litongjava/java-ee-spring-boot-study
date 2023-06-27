package com.litongjava.examples;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.EasyExcel;

public class NoModelDataExport {
  public static void main(String[] args) {
    // 文件输出位置
    String fileName = "noModelDataExport.xlsx";

    // 准备数据
    List<Map<Integer, String>> data = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Map<Integer, String> rowData = new HashMap<>();
      rowData.put(0, "字符串" + i);
      rowData.put(1, new Date().toString());
      rowData.put(2, Double.toString(0.56));
      data.add(rowData);
    }

    // 写入数据
    List<List<String>> heads = head("字符串标题", "日期标题", "数字标题");
    List<List<Object>> datas = dataList(data);
    System.out.println(heads);
    System.out.println(datas);

    EasyExcel.write(fileName).head(heads).sheet("NoModelDataExport").doWrite(datas);
  }

  private static List<List<String>> head(String... heads) {
    // 写入表头
    List<List<String>> list = new ArrayList<>();
    for (String headString : heads) {
      List<String> head = new ArrayList<>();
      head.add(headString);
      list.add(head);
    }
    return list;
  }

  private static List<List<Object>> dataList(List<Map<Integer, String>> data) {
    List<List<Object>> dataList = new ArrayList<>();
    for (Map<Integer, String> rowData : data) {
      List<Object> row = new ArrayList<>();
      for (int i = 0; i < rowData.size(); i++) {
        row.add(rowData.get(i));
      }
      dataList.add(row);
    }
    return dataList;
  }
}