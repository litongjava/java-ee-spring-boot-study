package com.litong.java.sprong.boot.tomcat.listener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author litong
 * @date 2020年9月14日_下午12:03:42 
 * @version 1.0 
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private Integer id;
  private String username;
}
