package com.uairobot.bill.study.spring.boot.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file")
public class UploadController {
  @Autowired
  private UploadService us;

  @RequestMapping("upload")
  public UploadFileEntity upload(@RequestParam(value = "file") MultipartFile file) {
    UploadFileEntity upload = us.upload(file);
    return upload;
  }

}
