package top.ppnt.sprong.boot.https.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by Ping E Lee on 2022年4月4日 下午7:19:55
 */
@RestController
@RequestMapping("/")
public class IndexController {

  @RequestMapping
  public String index() {
    return "index";
  }

  @RequestMapping("jvm")
  public String jvm() {
    long jvmTotal = Runtime.getRuntime().totalMemory();
    long jvmFree = Runtime.getRuntime().freeMemory();
    long jvmUsed = jvmTotal - jvmFree;
    String text = jvmUsed / 1024 / 1024 + "MB";
    return text;
  }
}
