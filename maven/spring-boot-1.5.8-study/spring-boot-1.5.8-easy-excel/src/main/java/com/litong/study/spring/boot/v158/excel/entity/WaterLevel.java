package com.litong.study.spring.boot.v158.excel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 新版水位流量信息表
 * </p>
 *
 * @author litong
 * @since 2020-06-04
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class WaterLevel implements Serializable {

  @TableId(type = IdType.UUID)
  private String id;

  private String siteName;

  private String level;

  private String time;

}