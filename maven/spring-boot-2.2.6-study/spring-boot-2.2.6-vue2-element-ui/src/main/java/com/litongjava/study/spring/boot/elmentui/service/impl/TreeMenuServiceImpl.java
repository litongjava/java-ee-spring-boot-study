package com.litongjava.study.spring.boot.elmentui.service.impl;

import com.litongjava.study.spring.boot.elmentui.entity.TreeMenu;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.litongjava.study.spring.boot.elmentui.dao.TreeMenuDao;
import com.litongjava.study.spring.boot.elmentui.service.TreeMenuService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * 树形菜单(TreeMenu)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 18:42:09
 */
@Service("treeMenuService")
public class TreeMenuServiceImpl implements TreeMenuService {
  @Resource
  private TreeMenuDao treeMenuDao;

  /**
   * 通过ID查询单条数据
   *
   * @param menuId 主键
   * @return 实例对象
   */
  @Override
  public TreeMenu queryById(Long menuId) {
    return this.treeMenuDao.queryById(menuId);
  }

  /**
   * 分页查询
   *
   * @param treeMenu    筛选条件
   * @param pageRequest 分页对象
   * @return 查询结果
   */
  @Override
  public Page<TreeMenu> queryByPage(TreeMenu treeMenu, PageRequest pageRequest) {
    long total = this.treeMenuDao.count(treeMenu);
    return new PageImpl<>(this.treeMenuDao.queryAllByLimit(treeMenu, pageRequest), pageRequest, total);
  }

  /**
   * 新增数据
   *
   * @param treeMenu 实例对象
   * @return 实例对象
   */
  @Override
  public TreeMenu insert(TreeMenu treeMenu) {
    this.treeMenuDao.insert(treeMenu);
    return treeMenu;
  }

  /**
   * 修改数据
   *
   * @param treeMenu 实例对象
   * @return 实例对象
   */
  @Override
  public TreeMenu update(TreeMenu treeMenu) {
    this.treeMenuDao.update(treeMenu);
    return this.queryById(treeMenu.getMenuId());
  }

  /**
   * 通过主键删除数据
   *
   * @param menuId 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Long menuId) {
    return this.treeMenuDao.deleteById(menuId) > 0;
  }

  @Override
  public List<TreeMenu> queryAll() {
    return treeMenuDao.selectList(null);
  }

  /**
   * 把数据组合成树形结构
   */
  @Override
  public List<TreeMenu> listWithTree() {
    List<TreeMenu> lists = treeMenuDao.selectList(null);

    List<TreeMenu> result = lists.stream()
        // 查找第一级菜单
        .filter(meun -> meun.getMeunLevel() == 1)
        // 查找子菜单并放到第一级菜单中
        .map(menu -> {
          menu.setTreeMenu(getChildren(menu, lists));
          return menu;

        })// 根据排序字段排序
        .sorted((menu1, menu2) -> {
          return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        })// 把处理结果收集成一个 List 集合
        .collect(Collectors.toList());
    return result;

  }

  /**
   * 递归获取子菜单
   * @paramroot 当前菜单
   * @paramall 总的数据
   * @return子菜单
   **/

  public List<TreeMenu> getChildren(TreeMenu root, List<TreeMenu> all) {
    List<TreeMenu> children = all.stream()
        // 根据 父菜单 ID 查找当前菜单 ID，以便于找到 当前菜单的子菜单
        .filter(menu -> menu.getParentMenuId() == root.getMenuId())
        // 递归查找子菜单的子菜单
        .map((menu) -> {
          menu.setTreeMenu(getChildren(menu, all));
          return menu;
        })
        // 根据排序字段排序
        .sorted((menu1, menu2) -> {
          return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        })
        // 把处理结果收集成一个 List 集合
        .collect(Collectors.toList());
    return children;
  }
}
