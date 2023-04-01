package top.ppnt.spring.boot.beel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ping E Lee
 *
 */
@Controller
@RequestMapping("")
public class IndexController {
  
  @RequestMapping("")
  public String index() {
    return "index";
  }

}
