package com.uairobot.bill.study.spring.boot.upload;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "upload_file")
public class UploadFileEntity {
  @Id
  private String md5;
  private String uri;

  @Transient
  private byte[] bytes;
  @Transient
  // 包含工程名的uri,/开头
  private String fullUri;
  @Transient
  // 可以下载的url
  public String url;
  @Transient
  private String localFilePath;
}