package com.litong.sutdy.spring.boot.v207.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import oracle.core.lvf.VersionMgr;

/**
 * @author bill robot
 * @date 2020年5月23日_下午10:16:19 
 * @version 1.0 
 * @desc
 */
//@SpringBootApplication
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);

  }

  @RequestMapping("/")
  public String index() {
    VersionMgr versionMgr = new VersionMgr();
    System.out.println(versionMgr.getClass().getName());
    return versionMgr.getClass().getName();
  }
}
