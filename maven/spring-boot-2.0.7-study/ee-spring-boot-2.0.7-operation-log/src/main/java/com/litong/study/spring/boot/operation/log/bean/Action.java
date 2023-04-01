package com.litong.study.spring.boot.operation.log.bean;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Action {
  private String actionDesc,actionType,actionIp,userId;
  private Date actionTime;
}
