package com.litong.study.spring.boot.v158.excel.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bill robot
 * @date 2020年6月4日_下午6:00:02 
 * @version 1.0 
 * @desc
 */
public class StrKit {

  /**
   * 删除空格和换行
   * @param str
   * @return
   */
  public static String replaceBlank(String str) {
    String dest = "";
    if (str != null) {
      Pattern p = Pattern.compile("\\s*|\t|\r|\n");
      Matcher m = p.matcher(str);
      dest = m.replaceAll("");
    }
    return dest;
  }

  public static String replaceChinses(String str) {
    String reg = "[\u4e00-\u9fa5]";
    Pattern pat = Pattern.compile(reg);
    Matcher mat = pat.matcher(str);
    String result = mat.replaceAll("");
    return result;
  }

  public static boolean isEmpty(String value) {
    return isEmpty((CharSequence) value);
  }

  public static boolean isEmpty(CharSequence value) {
    if (value == null || value.length() == 0) {
      return true;
    }
    return false;
  }

}
