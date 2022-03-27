package com.litong.study.spring.boot.v158.excel.utils.excel;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.litong.study.spring.boot.v158.excel.entity.WaterLevelRowModel;

/***
 *  监听器
 */
public class ExcelModelListener extends AnalysisEventListener<WaterLevelRowModel> {

  /**
   * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
   */
  private static final int BATCH_COUNT = 5;
  List<WaterLevelRowModel> datas = new ArrayList<WaterLevelRowModel>();
  private static int count = 1;

  @Override
  public void invoke(WaterLevelRowModel data, AnalysisContext context) {
    System.out.println("解析到一条数据:{ " + data.toString() + " }");
    datas.add(data);
    count++;
    if (datas.size() >= BATCH_COUNT) {
      saveData(count);
      datas.clear();
    }
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext context) {
    saveData(count);
    System.out.println("所有数据解析完成！");
    System.out.println(" count ：" + count);
  }

  /**
   * 加上存储数据库
   */
  private void saveData(int count) {
    System.out.println("{ " + count + " }条数据，开始存储数据库！" + datas.size());
    System.out.println("存储数据库成功！");
  }
}