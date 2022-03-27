package com.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api")
@Slf4j
public class UploadController {

  @RequestMapping("upload")
  public String upload(String user, String pswd, MultipartFile photo) {
    String fileName = photo.getOriginalFilename();
    log.info(user + "," + pswd + "," + fileName);
    File upload = new File("upload");
    if (!upload.exists())
      upload.mkdir();
    log.info(upload.getAbsolutePath());
    try {
      photo.transferTo(new File(upload.getAbsoluteFile() + File.separator + photo.getName()));
      return "success";
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "false";
  }
}
