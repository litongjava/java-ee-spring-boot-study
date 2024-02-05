package demo.controller;

import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/")
public class HelloWordWebFluxController {

  @RequestPath
  public String index() {
    return "index";
  }
}