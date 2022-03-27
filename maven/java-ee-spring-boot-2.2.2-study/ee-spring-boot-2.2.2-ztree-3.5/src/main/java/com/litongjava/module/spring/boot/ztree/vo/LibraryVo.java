package com.litongjava.module.spring.boot.ztree.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryVo {
  // moveType"inner"：成为子节点，"prev"：成为同级前一个节点，"next"：成为同级后一个节点
  private String id, targetId, pId, targetPId, moveType;
}