package com.litongjava.study.spring.boot.elmentui.service;

import com.litongjava.study.spring.boot.elmentui.entity.TreeMenu;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 树形菜单(TreeMenu)表服务接口
 *
 * @author makejava
 * @since 2023-04-01 18:42:09
 */
public interface TreeMenuService {

  /**
   * 通过ID查询单条数据
   *
   * @param menuId 主键
   * @return 实例对象
   */
  TreeMenu queryById(Long menuId);

  /**
   * 分页查询
   *
   * @param treeMenu    筛选条件
   * @param pageRequest 分页对象
   * @return 查询结果
   */
  Page<TreeMenu> queryByPage(TreeMenu treeMenu, PageRequest pageRequest);

  /**
   * 新增数据
   *
   * @param treeMenu 实例对象
   * @return 实例对象
   */
  TreeMenu insert(TreeMenu treeMenu);

  /**
   * 修改数据
   *
   * @param treeMenu 实例对象
   * @return 实例对象
   */
  TreeMenu update(TreeMenu treeMenu);

  /**
   * 通过主键删除数据
   *
   * @param menuId 主键
   * @return 是否成功
   */
  boolean deleteById(Long menuId);

  List<TreeMenu> queryAll();

  /** 
   * 查询数据库数据，并处理后返回 树形数据 
   * @return树形数据
   **/
  List<TreeMenu> listWithTree();

}
