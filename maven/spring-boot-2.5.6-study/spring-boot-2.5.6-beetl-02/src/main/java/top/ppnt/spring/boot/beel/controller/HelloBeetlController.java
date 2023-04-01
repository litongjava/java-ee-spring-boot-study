package top.ppnt.spring.boot.beel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/home")
@Slf4j
public class HelloBeetlController {

  /**
   * 测试beetl模板
   *
   * @return
   */
  @RequestMapping("/add")
  public String home(Model model) {
    log.info("add request");
    model.addAttribute("email", "litongjava@qq.com");
    return "add";
  }
  
}