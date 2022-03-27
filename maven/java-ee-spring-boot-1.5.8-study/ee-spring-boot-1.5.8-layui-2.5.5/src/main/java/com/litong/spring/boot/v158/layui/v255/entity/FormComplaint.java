package com.litong.spring.boot.v158.layui.v255.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 投诉单
 * </p>
 *
 * @author litong
 * @since 2020-06-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FormComplaint extends Model<FormComplaint> {

  private static final long serialVersionUID = 1L;

  /**
   * 投诉ID
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /**
   * 服务编号
   */
  private String serviceCode;

  /**
   * 服务类型
   */
  private String compType;

  /**
   * 投诉订单号
   */
  private String compOrder;

  /**
   * 投诉原因
   */
  private String compReason;

  /**
   * 投诉详情
   */
  private String compDetail;

  /**
   * 投诉状态
   */
  private Integer complainState;

}
