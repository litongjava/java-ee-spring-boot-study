package com.litongjava.study.spring.boot.elmentui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 联系人
 * </p>
 *
 * @author litongjava
 * @since 2023-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("contact")
public class Contact implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @TableId(value="id",type = IdType.ASSIGN_ID)
  private Long id;

  /**
   * name
   */
  @TableField("name")
  private String name;

  /**
   * nick_name
   */
  @TableField("nick_name")
  private String nickName;

  /**
   * email
   */
  @TableField("email")
  private String email;

  /**
   * mobile
   */
  @TableField("mobile")
  private String mobile;

  /**
   * 创建者
   */
  @TableField("creator")
  private String creator;

  /**
   * 创建时间
   */
  @TableField("create_time")
  private Date createTime;

  /**
   * 更新者
   */
  @TableField("updater")
  private String updater;

  /**
   * 更新时间
   */
  @TableField("update_time")
  private Date updateTime;

  /**
   * 是否删除
   */
  @TableField("deleted")
  private Boolean deleted;

  /**
   * tenant_id
   */
  @TableField("tenant_id")
  private Long tenantId;

}
