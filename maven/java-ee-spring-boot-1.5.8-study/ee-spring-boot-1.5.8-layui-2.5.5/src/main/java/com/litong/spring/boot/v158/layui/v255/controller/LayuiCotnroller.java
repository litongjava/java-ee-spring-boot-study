package com.litong.spring.boot.v158.layui.v255.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.litong.spring.boot.v158.mp.vo.JsonBean;

/**
 * @author bill robot
 * @date 2020年6月17日_下午9:55:18 
 * @version 1.0 
 * @desc
 */
@RequestMapping("layui")
@RestController
public class LayuiCotnroller {
  @RequestMapping("/getCity")
  @ResponseBody
  public JsonBean<Map<String,String>> getCity() {
    Map<String,String> city=new HashMap<>();
    city.put("beijing", "北京");
    city.put("shanghai","上海");
    city.put("guagnzhou","广州");
    city.put("shenzhen","深圳");
    return new JsonBean<Map<String,String>>(city);
  }
}
