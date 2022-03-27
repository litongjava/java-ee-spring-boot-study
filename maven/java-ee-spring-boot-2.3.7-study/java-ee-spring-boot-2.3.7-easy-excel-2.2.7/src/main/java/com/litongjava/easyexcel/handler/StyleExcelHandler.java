package com.litongjava.easyexcel.handler;

import java.math.BigDecimal;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

import com.alibaba.excel.write.handler.WriteHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StyleExcelHandler implements WriteHandler {

  public void cell(int i, Cell cell) {
    log.info("cell:{}", cell);
    // 从第3行开始设置格式，第一行和第二行是表头
    Workbook workbook = cell.getSheet().getWorkbook();
    CellStyle cellStyle = createStyle(workbook);
    if (cell.getRowIndex() > 3) {
      if (i == 5) {
        DataFormat dataFormat = workbook.createDataFormat();
        // 设置千位分隔符
        cellStyle.setDataFormat(dataFormat.getFormat("#,##0"));
      }
      if (i == 7 || i == 6) {
        String stringCellValue = cell.getStringCellValue();
        cell.setCellValue(
            new BigDecimal(stringCellValue.replaceAll("%", "")).divide(new BigDecimal(100), 8, BigDecimal.ROUND_HALF_UP)
                .setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
        // 设置百分比
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
      }
      if (i == 0 || i == 3) {
        cell.setCellValue(Long.parseLong(cell.getStringCellValue()));
      }
    }
    cell.getRow().getCell(i).setCellStyle(cellStyle);
  }

  /**
    * 实际中如果直接获取原单元格的样式进行修改, 最后发现是改了整行的样式, 因此这里是新建一个样* 式
    */
  private CellStyle createStyle(Workbook workbook) {
    CellStyle cellStyle = workbook.createCellStyle();
    // 下边框
    cellStyle.setBorderBottom(BorderStyle.THIN);
    // 左边框
    cellStyle.setBorderLeft(BorderStyle.THIN);
    // 上边框
    cellStyle.setBorderTop(BorderStyle.THIN);
    // 右边框
    cellStyle.setBorderRight(BorderStyle.THIN);
    // 水平对齐方式
    cellStyle.setAlignment(HorizontalAlignment.CENTER);
    // 垂直对齐方式
    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    return cellStyle;
  }
}