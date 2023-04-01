package com.litong.study.spring.boot.v158.excel.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * litong's ReflectionUtils
 * @author bill robot
 * @date 2020年6月4日 下午7:31:31
 * @desc
 */
public class LReflectionUtils {
  /**
   * 执行实体类的get方法
   * @param <T>
   * @param model
   * @param field
   * @return
   */
  public static <T> Object invokeGetMethod(T model, Field field) {
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
