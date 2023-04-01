package com.litong.study.spring.boot.v158.excel.entity;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSON;
import com.litong.study.spring.boot.v158.excel.utils.StrKit;

/**
 * @author bill robot
 * @date 2020年6月4日_下午6:57:03 
 * @version 1.0 
 * @desc
 */
public class WaterLevelRowModelTest {

  String jsonString = "{\"column0\":\"2019\",\"column1\":\"1月份\",\"column10\":\"0.15\",\"column11\":\"-1.73\",\"column12\":\"2\",\"column13\":\"3.63\",\"column14\":\"2.94\",\"column15\":\"2.95\",\"column16\":\"2.47\",\"column17\":\"2.76\",\"column18\":\"1.9\",\"column19\":\"1.72\",\"column2\":\"1\",\"column20\":\"2.4\",\"column21\":\"30.81\",\"column3\":\"1\",\"column4\":\"1.5\",\"column5\":\"12.12\",\"column6\":\"-0.22\",\"column7\":\"172.15\",\"column8\":\"172.37\",\"column9\":\"172.16\"}";

  @Test
  public void test() {
    WaterLevelRowModel model = JSON.parseObject(jsonString, WaterLevelRowModel.class);
    
    Class<? extends WaterLevelRowModel> clazz = model.getClass();
    Field[] declaredFields = clazz.getDeclaredFields();
  }

  /**
   * 执行实体类的get方法
   * @param <T>
   * @param model
   * @param field
   * @return
   */
  private <T> Object invokeGetMethod(T model, Field field) {
    Class<? extends Object> clazz = model.getClass();
    PropertyDescriptor pd = null;
    try {
      pd = new PropertyDescriptor(field.getName(), clazz);
    } catch (IntrospectionException e) {
      e.printStackTrace();
    }
    Method readMethod = pd.getReadMethod();
    if (readMethod != null) {
      Object invoke = null;
      try {
        invoke = readMethod.invoke(model);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        e.printStackTrace();
      }
      return invoke;
    } else {
      return null;
    }
  }

}
