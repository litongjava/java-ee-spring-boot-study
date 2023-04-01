package com.litongjava.easyexcel.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;

public class StudentTest {
  public List<Student> getData() {
    List<Student> lists = new ArrayList<>();
    for (int i = 0; i <= 10; i++) {
      Student student = new Student();
      student.setId(i + 1);
      student.setName("李四" + i);
      student.setBirthday(new Date());
      student.setSalary(1500.00D);
      lists.add(student);
    }
    return lists;
  }

  
  @Test
  public void simpleWrite() {
    EasyExcel.write("学生信息表.xlsx", Student.class).sheet().doWrite(getData());
  }
  @Test
  void contextLoads() {
    // 设置 要导出列的属性名
    // 必须要跟类型的属性名保持一致
//    Set<String> set = new HashSet<>();
//    set.add("id");
//    set.add("name");

    EasyExcel.write("学生信息表.xlsx", Student.class)
        // .includeColumnFiledNames(set)
        // 自适应宽度，但是这个不是特别精确
        .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
        // 指定sheet,并写数据
        .sheet().doWrite(getData());
  }

  @Test
  void readExcel() throws Exception {

    List<Student_Read> list = new ArrayList<>();

    /*
     * EasyExcel 读取 是基于SAX方式 因此在解析时需要传入监听器
     */
    // 第一个参数 为 excel文件路径,第二个参数是读取时的数据类型,第三个参数是监听器
    String filename = "学生信息表" + ExcelTypeEnum.XLSX.getValue();
    EasyExcel.read(filename, Student_Read.class, new AnalysisEventListener<Student_Read>() {

      // 每读取一行就调用该方法
      @Override
      public void invoke(Student_Read data, AnalysisContext context) {
        list.add(data);
      }

      // 全部读取完成就调用该方法
      @Override
      public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("读取完成");
      }
    }).sheet().doRead();

    list.forEach(System.out::println);
  }
}
