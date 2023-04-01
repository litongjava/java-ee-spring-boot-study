package com.litong.study.spring.boot.operation.log.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
  private String id,username;
}
