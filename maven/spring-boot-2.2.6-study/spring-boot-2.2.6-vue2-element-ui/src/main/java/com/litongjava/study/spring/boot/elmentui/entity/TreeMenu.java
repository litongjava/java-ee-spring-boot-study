package com.litongjava.study.spring.boot.elmentui.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 树形菜单(TreeMenu)实体类
 *
 * @author makejava
 * @since 2023-04-01 18:42:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeMenu implements Serializable {
  private static final long serialVersionUID = 288505053740098201L;
  /**
   * 当前菜单ID
   */
  private Long menuId;
  /**
   * 菜单名
   */
  private String name;
  /**
   * 当前菜单的父菜单 ID
   */
  private Long parentMenuId;
  /**
   * 当前菜单的层级
   */
  private Integer meunLevel;
  /**
   * 排序
   */
  private Integer sort;

  /*** 用于保存一个菜单的子菜单*/
  @TableField(exist = false)
  private List<TreeMenu> treeMenu;

}
