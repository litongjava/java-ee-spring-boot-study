package com.litong.spring.boot.v158.layui.v255.utils;

import java.lang.reflect.Field;

/**
 * @author bill robot
 * @date 2020年6月9日_上午2:16:58 
 * @version 1.0 
 * @desc
 */
public class ReflectionUtils {
  /**
   * 如果属性中string的值是字符串,则转为null
   * @param <T>
   * @param e
   * @return
   */
  public static <T> T convertEmpytStringToNull(T e) {
    Field[] fields = e.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.getGenericType().toString().equals("class java.lang.String")) {
        field.setAccessible(true);
        Object obj = null;
        try {
          obj = field.get(e);
        } catch (IllegalArgumentException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        } catch (IllegalAccessException e1) {
          e1.printStackTrace();
        }
        if ("".equals(obj)) {
          try {
            field.set(e, null);
          } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
          } catch (IllegalAccessException e1) {
            e1.printStackTrace();
          }
        }
      }
    }
    return e;
  }

}
