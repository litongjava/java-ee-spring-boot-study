package com.litongjava.spring.boot.swagger;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Film {
  private String id;
  private String fileName;
  private Date createTime;
  private Date modifiedTime;
  private String modifierId;
  private Date publishTime;
  private Short score;
}
