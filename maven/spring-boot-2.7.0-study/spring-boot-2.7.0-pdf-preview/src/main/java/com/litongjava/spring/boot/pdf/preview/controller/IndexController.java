package com.litongjava.spring.boot.pdf.preview.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping({ "", "/" })
  public String index() {
    return "index";
  }

  @GetMapping("/preview")
  public void download(HttpServletResponse response) {
    String pathname = "F:\\my_file\\test_pdf";
    String filename = "44060122_8040416713.PDF";
    File file = new File(pathname, filename);
    String encode = null;
    try {
      encode = URLEncoder.encode(file.getName(), "UTF-8");
    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
    if (encode == null) {
      encode = "未知文件名";
    }

    response.setHeader("Content-Disposition", "inline; filename*=UTF-8''" + encode);

    ServletOutputStream os = null;
    try {
      os = response.getOutputStream();
      os.write(FileUtils.readFileToByteArray(file));
      os.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
