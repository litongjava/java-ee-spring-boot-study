package com.litongjava.study.spring.boot.elmentui.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litongjava.study.spring.boot.elmentui.entity.TreeMenu;
import com.litongjava.study.spring.boot.elmentui.model.Result;
import com.litongjava.study.spring.boot.elmentui.service.TreeMenuService;

/**
 * 树形菜单(TreeMenu)表控制层
 *
 * @author makejava
 * @since 2023-04-01 18:42:08
 */
@RestController
@RequestMapping("treeMenu")
public class TreeMenuController {
  /**
   * 服务对象
   */
  @Resource
  private TreeMenuService treeMenuService;

  /**
   * 分页查询
   *
   * @param treeMenu    筛选条件
   * @param pageRequest 分页对象
   * @return 查询结果
   */
  @GetMapping
  public ResponseEntity<Page<TreeMenu>> queryByPage(TreeMenu treeMenu, PageRequest pageRequest) {
    return ResponseEntity.ok(this.treeMenuService.queryByPage(treeMenu, pageRequest));
  }

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("{id}")
  public ResponseEntity<TreeMenu> queryById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(this.treeMenuService.queryById(id));
  }

  /**
   * 新增数据
   *
   * @param treeMenu 实体
   * @return 新增结果
   */
  @PostMapping
  public ResponseEntity<TreeMenu> add(TreeMenu treeMenu) {
    return ResponseEntity.ok(this.treeMenuService.insert(treeMenu));
  }

  /**
   * 编辑数据
   *
   * @param treeMenu 实体
   * @return 编辑结果
   */
  @PutMapping
  public ResponseEntity<TreeMenu> edit(TreeMenu treeMenu) {
    return ResponseEntity.ok(this.treeMenuService.update(treeMenu));
  }

  /**
   * 删除数据
   *
   * @param id 主键
   * @return 删除是否成功
   */
  @DeleteMapping
  public ResponseEntity<Boolean> deleteById(Long id) {
    return ResponseEntity.ok(this.treeMenuService.deleteById(id));
  }

  /**
   * 获取数据库所有数据
   * @return
   */
  @GetMapping("selectAll")
  public Result selectAll() {
    return Result.ok().data("items", treeMenuService.queryAll());

  }

  /**
   * 获取数据库数据，并处理成树形结构
   * @return 树形结构数据
   */
  @CrossOrigin
  @GetMapping("selectAllWithTree")
  public Result selectAllWithTree() {
    return Result.ok().data("items", treeMenuService.listWithTree());
  }
}
