package com.alit.seralizable.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author litong
 * @date 2019年5月1日_上午9:15:34
 * @version 1.0
 * @desc 加载目录下的静态文件
 */
public class ResourcesStaticServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setCharacterEncoding("UTF-8");
    String requestURI = request.getRequestURI();
    // 如果没有后缀名,调整到index.html
    if (requestURI.endsWith("/")) {
      response.sendRedirect(requestURI + "index.html");
      return;
    }
    // 获取文件名,设置mineType
    String filename = requestURI.substring(requestURI.lastIndexOf("/") + 1);
    String mimeType = request.getServletContext().getMimeType(filename);
    response.setContentType(mimeType);
    // 获取真实的文件路径
    String resourcesStaticPath = ResourcesStaticUtil.getResourcesStaticPath();
    filename = resourcesStaticPath + filename;

    // 基于FileInputStream 和OutputStream来实现,以byte流的方式打开文件
    try (FileInputStream fileInputStream = new FileInputStream(filename)) {
      int i = fileInputStream.available(); // 得到文件大小
      byte data[] = new byte[i];
      fileInputStream.read(data); // 读数据
      fileInputStream.close();
      OutputStream out = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
      out.write(data); // 输出数据
    }
  }
}