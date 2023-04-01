package com.litongjava.spring.boot.v151.easyui.curd.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyUIUtil {

  /**
   * {"success":true}
   *
   * @return
   */
  public static Map<String, Object> buildSuccess() {
    Map<String, Object> retval = new HashMap<>();
    retval.put("success", true);
    return retval;
  }

  /**
   * {"msg","失败的原因"}
   *
   * @param msg
   * @return
   */
  public static Map<String, Object> buildFail(String msg) {
    Map<String, Object> retval = new HashMap<>();
    retval.put("msg", msg);
    return retval;
  }

  public static Map<String, Object> buildList(long totalElements, List<?> content) {
    Map<String, Object> retval = new HashMap<String, Object>();
    retval.put("total", totalElements);
    retval.put("rows", content);
    return retval;
  }
}