package com.uairobot.bill.study.spring.boot.upload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UploadService {
  @Autowired
  private FileSaveSerivce fileSaveSerivce;
  @Autowired
  private UploadFileRepository uploadFileRepository;
  @Autowired
  private IPUtilService ipUtilService;
  @Autowired
  private ServerProperties sp;

  /**
   * 1.获取文件md5,查询数据库 2.数据库中不存在,上传文件,保存到数据,返回
   * 
   */
  public UploadFileEntity upload(MultipartFile file) {
    byte[] bytes = null;
    try {
      bytes = file.getBytes();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 文件名
    String originalFileName = file.getOriginalFilename();
    // 拓展名
    String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
    UploadFileEntity upload = upload(bytes, extension, null);
    return upload;

  }

  /**
   * 
   * @param bytes
   * @param extension
   * @param folder
   *          如果没有,设置为null
   * @return
   */
  public UploadFileEntity upload(byte[] bytes, String extension, String folder) {
    String thisUrl = ipUtilService.getThisUrl();
    UploadFileEntity retval = null;
    String md5Hex = DigestUtils.md5Hex(bytes);
    // f124
    Optional<UploadFileEntity> optional = uploadFileRepository.findByMd5(md5Hex);
    if (optional.isPresent()) {
      retval = optional.get();
      //retval.setBytes(bytes);
      retval.setFullUri(getFullUri(retval.getUri()));
      retval.setUrl(thisUrl + "/" + retval.getUri());
      //retval.setLocalFilePath(getLocalFilePath(retval.getUri()));
      log.info("从数据库中查找到:" + retval.getUri());
      return retval;
    }
    // 文件输入流
    ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
    String uri = fileSaveSerivce.saveInputStream(inputStream, bytes.length, extension, folder);

    log.info("上传文件成功:{},保存位置,{}", uri,getLocalFilePath(uri));
    retval = new UploadFileEntity();
    retval.setMd5(md5Hex);
    retval.setUri(uri);
    retval.setUrl(thisUrl + "/" + uri);
    retval.setFullUri(getFullUri(uri));
    // retval.setLocalFilePath(getLocalFilePath(retval.getUri()));
    uploadFileRepository.save(retval);
    return retval;
  }

  private String getLocalFilePath(String uri) {
    String uploadPath = fileSaveSerivce.getHomeDir();
    return uploadPath + File.separator + uri;
  }

  public String getFullUriById(String md5Hex) {
    Optional<UploadFileEntity> entity = uploadFileRepository.findByMd5(md5Hex);
    String uri = null;
    if (entity.isPresent()) {
      uri = entity.get().getUri();
      // log.info("从数据中查询到到图片:" + md5Hex+","+uri);
    }
    uri = getFullUri(uri);
    return uri;
  }

  private String getFullUri(String uri) {
    return sp.getContextPath()+"/"+ uri;
  }
}