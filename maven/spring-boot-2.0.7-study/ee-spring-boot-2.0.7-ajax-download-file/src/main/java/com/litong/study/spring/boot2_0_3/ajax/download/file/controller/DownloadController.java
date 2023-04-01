package com.litong.study.spring.boot2_0_3.ajax.download.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("download")
@CrossOrigin(origins="*")
public class DownloadController {

  @RequestMapping
  public void download(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String fileName = req.getParameter("fileName");
    File file = new File(System.getProperty("user.home"), fileName);
    // 防止下载文件乱码
    String string = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
    resp.setHeader("Content-Disposition","attachment;filename=" + string);
    resp.setContentLength((int) file.length());

    try (FileInputStream fis = new FileInputStream(file); 
        ServletOutputStream sos = resp.getOutputStream();) {
      byte[] buffer = new byte[128];
      int count = 0;
      while ((count = fis.read(buffer)) > 0) {
        sos.write(buffer, 0, count);
      }
      sos.flush();
    }
  }
}
