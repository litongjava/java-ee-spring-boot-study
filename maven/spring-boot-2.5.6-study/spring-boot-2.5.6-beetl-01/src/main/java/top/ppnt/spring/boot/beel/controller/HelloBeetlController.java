package top.ppnt.spring.boot.beel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
/*  public ModelAndView home() {

    ModelAndView modelAndView = new ModelAndView();
    log.info("add request");
    modelAndView.addObject("email", "apk2sf@163.com");
    modelAndView.setViewName("add");
    return modelAndView;
  }*/
  public ModelAndView home( ) {
    ModelAndView modelAndView = new ModelAndView();
    log.info("add request");
    modelAndView.addObject("email", "apk2sf@163.com");
    modelAndView.setViewName("add");
    return modelAndView;
  }
}
