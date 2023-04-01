package com.litongjava.easyexcel.handler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.litongjava.easyexcel.model.BasePurchaseExecutionResponse;

/**
 * @author create by Ping E Lee on 2022年3月25日 下午12:13:49 
 *
 */
@SuppressWarnings("deprecation")
public class StyleExcelHandlerTest {

  @Test
  public void test3() throws IOException {
    StyleExcelHandler handler = new StyleExcelHandler();

//    ExcelWriterBuilder excelBuilder = EasyExcel.write("学生信息表.xlsx", BasePurchaseExecutionResponse.class);
//    excelBuilder.registerWriteHandler(handler);
//    ExcelWriterSheetBuilder sheetBuilder = excelBuilder.sheet();
//    sheetBuilder.doWrite(createResponseList());

    OutputStream outputStream = new FileOutputStream("D://2007.xlsx");
    // 这里要把上面创建的样式类通过构造函数传入
    ExcelWriter writer = new ExcelWriter(null, outputStream, ExcelTypeEnum.XLSX, true, handler);
    Sheet sheet1 = new Sheet(1, 1, BasePurchaseExecutionResponse.class, "含供应商和地区", null);
    sheet1.setAutoWidth(true);
    writer.write(createResponseList(), sheet1);
    writer.finish();
    outputStream.close();
  }

  /**
   * 创建数据集合
   *
   * @return
   */
  private List<BasePurchaseExecutionResponse> createResponseList() {
    List<BasePurchaseExecutionResponse> responses = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      BasePurchaseExecutionResponse response = new BasePurchaseExecutionResponse().setTotalShipment(i * 1000000)
          .setConfirmDeliverRate(i + "%").setNum(String.valueOf(i)).setProductSeason("冬").setProductYear("19")
          .setSupplierType("本厂").setBrandNameListString("耐特").setPlanDeliverRate(i * 2 + "%");
      responses.add(response);
    }
    return responses;
  }

}
