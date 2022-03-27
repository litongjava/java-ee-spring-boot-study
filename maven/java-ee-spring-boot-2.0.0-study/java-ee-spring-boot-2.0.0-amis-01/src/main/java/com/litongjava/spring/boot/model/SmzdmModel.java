package com.litongjava.spring.boot.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("smzdm")
public class SmzdmModel {
  /**
   * 标题
   */
  private String title;
  /**
   * 价格
   */
  private String price;
  /**
   * 简介
   */
  private String introduce;
  /**
   * 认为值的人数
   */
  private String zhi;
  /**
   * 认为不值得人数
   */
  // TODO:对应的数据库列的名字，可自行更改
  @TableField(value = "noZhi")
  private String noZhi;
  /**
   * 收藏的人数
   */
  private String start;
  /**
   * 评论数
   */
  private String pl;
  /**
   * 发布时间
   */
  private String fbtime;
  /**
   * url
   */
  private String url;
  /**
   * 图床链接
   */
  private String imgurl;
}
