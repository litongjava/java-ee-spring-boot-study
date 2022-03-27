package com.litong.spring.boot.v158.layui.v255.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bill robot
 * @date 2020年6月9日_上午10:40:42 
 * @version 1.0 
 * @desc
 */

public class LArrays {
  public static List<Integer> toList(int[] ids) {
    List<Integer> idList = new ArrayList<>(ids.length);
    for (int i : ids) {
      idList.add(i);
    }
    return idList;
  }
}
