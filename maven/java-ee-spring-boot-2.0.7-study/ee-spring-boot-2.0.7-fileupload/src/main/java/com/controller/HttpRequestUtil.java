package com.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

public class HttpRequestUtil {
  private String uploadPath = "upload";

  public void processUpload(HttpServletRequest request) {
    File uploadFile = new File(uploadPath);
    if (!uploadFile.exists()) {
      uploadFile.mkdirs();
    }
    try {
      request.setCharacterEncoding("utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    // 检测是不是存在上传文件,如果存在,之家返回
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    if (!isMultipart) {
      System.out.println("不包含生上传文件");
      return;
    }
    List<FileItem> items = getFileItem(request);
    if (items == null) {
      System.out.println("获取到的FileItem为null");
      return;
    }
    // 解析表单项目
    Iterator<FileItem> iterator = items.iterator();
    while (iterator.hasNext()) {
      FileItem item = iterator.next();
      // 如果是普通表单属性
      if (item.isFormField()) {
        // 相当于input的name属性 <input type="text" name="content">
        String name = item.getFieldName();
        // input的value属性
        String value = item.getString();
        System.out.println("属性:" + name + " 属性值:" + value);
      } else {// 如果是上传文件
        // 属性名
        String fieldName = item.getFieldName();
        // 上传文件路径
        String fileName = item.getName();
        System.out.println("属性:" + fieldName + ",文件名" + fileName);
        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);// 获得上传文件的文件名
        String fullPath = uploadPath + File.separator + fileName;
        try {
          item.write(new File(fullPath, fileName));
          System.out.println("文件保存到:" + fullPath);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  private List<FileItem> getFileItem(HttpServletRequest request) {
    DiskFileItemFactory factory = new DiskFileItemFactory();
    // 指定在内存中缓存数据大小,单位为byte,这里设为1Mb
    factory.setSizeThreshold(1024 * 1024);
    // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
    factory.setRepository(new File("D:\\temp"));
    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);
    // 指定单个上传文件的最大尺寸,单位:字节，这里设为50Mb
    upload.setFileSizeMax(50 * 1024 * 1024);
    // 指定一次上传多个文件的总尺寸,单位:字节，这里设为50Mb
    upload.setSizeMax(50 * 1024 * 1024);
    upload.setHeaderEncoding("UTF-8");

    List<FileItem> items = null;
    ServletRequestContext requestContent = new ServletRequestContext(request);
    try {
      // 解析request请求
      items = upload.parseRequest(requestContent);
    } catch (FileUploadException e) {
      e.printStackTrace();
    }
    return items;
  }
}
