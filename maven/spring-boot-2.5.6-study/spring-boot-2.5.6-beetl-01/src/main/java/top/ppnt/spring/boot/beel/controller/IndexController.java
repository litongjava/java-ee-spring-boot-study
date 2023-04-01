package top.ppnt.spring.boot.beel.controller;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
  @Autowired
  private BeetlGroupUtilConfiguration beetlGroupUtilConfiguration;
  @RequestMapping("")
  public String index() {
    GroupTemplate groupTemplate = beetlGroupUtilConfiguration.getGroupTemplate();
    return "index";
  }
}