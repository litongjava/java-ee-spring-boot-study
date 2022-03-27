package com.uairobot.bill.study.spring.boot.upload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.ApplicationHome;
import org.springframework.stereotype.Service;

@Service
public class FileSaveSerivce {
  private String projectUploadDirName="upload";
  private String projectHomeDir=null;
  private String uploadPath=null;
  public String getUploadPath(){
    if(uploadPath==null){
      uploadPath = getHomeDir() + File.separator + projectUploadDirName;
    }
    return uploadPath;
  }
  public String getHomeDir(){
    if(projectHomeDir==null){
      ApplicationHome applicationHome = new ApplicationHome();
      File dir = applicationHome.getDir();
      projectHomeDir=dir.getAbsolutePath();
    }
    return projectHomeDir;
  }
  public String saveInputStream(InputStream inputStream, int length, String extension, String subfolder) {
    String uploadDir = getUploadPath();
    String subDir = generateSubdir(subfolder);
    String filename = getFilename(extension);
    String uri = getFileUri(subDir, filename);
    String folderPath = uploadDir + File.separator + subDir;
    File folder = new File(folderPath);
    if (!folder.exists()) {
      folder.mkdirs();
    }
    String fullPath = folderPath + File.separator + filename;
    int bufferSize = 1024 * 1024;
    byte[] buffer = new byte[bufferSize];
    BufferedOutputStream outputStream = null;
    try {
      outputStream = new BufferedOutputStream(new FileOutputStream(fullPath));
      if ((length = inputStream.read(buffer, 0, bufferSize)) != -1) {
        outputStream.write(buffer, 0, length);
      }
    } catch (IOException e1) {
      e1.printStackTrace();
    } finally {
      IOUtils.closeQuietly(outputStream);
    }
    return uri;
  }

  /**
   * 根据年夜日时,生成子文件
   * 
   * @param subfolder
   * @return
   */
  public String generateSubdir(String subfolder) {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR); // 获取年
    int month = calendar.get(Calendar.MONTH) + 1;// 获取月
    int day = calendar.get(Calendar.DAY_OF_MONTH); // 获取天
    int hour = calendar.get(Calendar.HOUR_OF_DAY); // 获取小时
    if(subfolder==null){
      return apppend(File.separator, year, month, day, hour);
    }else{
      return apppend(File.separator, year, month, day, hour, subfolder);
    }
    
  }

  private String apppend(String separator, int year, int month, int day, int hour) {
    String str = year + separator + month + separator + day + separator + hour;
    return str;
  }
  private String apppend(String separator, int year, int month, int day, int hour, String subfolder) {
    String str = year + separator + month + separator + day + separator + hour + separator + subfolder;
    return str;
  }

  private String getFileUri(String subDir, String filename) {
    // 返回文件下载路径,不包含工程名称
    return projectUploadDirName + File.separator + subDir + File.separator + filename;
  }

  public String getFilename(String extension) {
    long currentTimeMillis = System.currentTimeMillis();
    String fileName = currentTimeMillis + "." + extension;
    return fileName;
  }

}