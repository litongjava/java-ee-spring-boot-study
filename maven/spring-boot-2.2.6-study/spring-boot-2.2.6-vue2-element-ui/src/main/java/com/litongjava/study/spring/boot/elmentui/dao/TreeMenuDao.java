package com.litongjava.study.spring.boot.elmentui.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.litongjava.study.spring.boot.elmentui.entity.TreeMenu;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 树形菜单(TreeMenu)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-01 18:42:08
 */
@Mapper
public interface TreeMenuDao extends BaseMapper<TreeMenu> {

  /**
   * 通过ID查询单条数据
   *
   * @param menuId 主键
   * @return 实例对象
   */
  TreeMenu queryById(Long menuId);

  /**
   * 查询指定行数据
   *
   * @param treeMenu 查询条件
   * @param pageable 分页对象
   * @return 对象列表
   */
  List<TreeMenu> queryAllByLimit(TreeMenu treeMenu, @Param("pageable") Pageable pageable);

  /**
   * 统计总行数
   *
   * @param treeMenu 查询条件
   * @return 总行数
   */
  long count(TreeMenu treeMenu);

  /**
   * 新增数据
   *
   * @param treeMenu 实例对象
   * @return 影响行数
   */
  int insert(TreeMenu treeMenu);

  /**
   * 批量新增数据（MyBatis原生foreach方法）
   *
   * @param entities List<TreeMenu> 实例对象列表
   * @return 影响行数
   */
  int insertBatch(@Param("entities") List<TreeMenu> entities);

  /**
   * 批量新增或按主键更新数据（MyBatis原生foreach方法）
   *
   * @param entities List<TreeMenu> 实例对象列表
   * @return 影响行数
   * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
   */
  int insertOrUpdateBatch(@Param("entities") List<TreeMenu> entities);

  /**
   * 修改数据
   *
   * @param treeMenu 实例对象
   * @return 影响行数
   */
  int update(TreeMenu treeMenu);

  /**
   * 通过主键删除数据
   *
   * @param menuId 主键
   * @return 影响行数
   */
  int deleteById(Long menuId);

}
