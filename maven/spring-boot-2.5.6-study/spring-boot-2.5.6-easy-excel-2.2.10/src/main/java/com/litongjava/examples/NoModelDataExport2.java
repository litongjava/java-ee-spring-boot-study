package com.litongjava.examples;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

public class NoModelDataExport2 {
  public static void main(String[] args) {
    // 文件输出位置
    String fileName = "noModelDataExport2.xlsx";

    // 准备数据
    List<Map<Integer, String>> data = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Map<Integer, String> rowData = new HashMap<>();
      rowData.put(0, "字符串" + i);
      rowData.put(1, new Date().toString());
      rowData.put(2, Double.toString(0.56));
      data.add(rowData);
    }

    // 写入数据到不同的sheet
    List<List<String>> heads = head("字符串标题", "日期标题", "数字标题");
    List<List<Object>> datas = dataList(data);

    // 创建excel writer对象
    ExcelWriter excelWriter = EasyExcel.write(fileName).build();

    // 使用一个循环，每次创建一个新的sheet并写入数据
    for (int i = 0; i < 3; i++) {
      WriteSheet writeSheet = EasyExcel.writerSheet("Sheet" + i).head(heads).build();
      excelWriter.write(datas, writeSheet);
    }

    // 最后一定要关闭excelWriter
    excelWriter.finish();
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
