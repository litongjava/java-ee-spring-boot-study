package com.litongjava.module.spring.boot.ztree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.litong.utils.string.UUIDUtils;
import com.litongjava.module.spring.boot.ztree.model.TreeMenu;
import com.litongjava.module.spring.boot.ztree.service.TreeMenuService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author litong
 * @since 2021-04-11
 */
@Controller
@RequestMapping("/tree/menu")
public class TreeMenuController {

  @Autowired
  public TreeMenuService treeMenuService;

  /**
  * 查询所有分类信息
  * @return
  */
  @RequestMapping(value = "list")
  @ResponseBody
  public List<TreeMenu> list() {
    return treeMenuService.list();
  }

  /**
  * 保存分类
  * @return
  */
  @RequestMapping(value = "save")
  @ResponseBody
  public String save(TreeMenu entity) {
    String id = UUIDUtils.random();
    entity.setId(id);
    treeMenuService.save(entity);
    return id;
  }
  

  /**
  * 保存分类
  * @return
  */
  @RequestMapping(value = "saveList")
  @ResponseBody
  public boolean saveList(@RequestBody List<TreeMenu> trees) {
    return  treeMenuService.saveBatch(trees);
  }

  /**
  * 删除分类
  * @return
  */
  @RequestMapping(value = "removeById")
  @ResponseBody
  public boolean removeById(String id) {
    boolean removeById = treeMenuService.removeById(id);
    return removeById;
  }
  
  
  @RequestMapping(value = "updateById")
  @ResponseBody
  public boolean removeById(TreeMenu entity) {
    String userId = "46171f3814384f72b8284a43b25cb64f";
    entity.setCreateUser(userId);
    return treeMenuService.updateById(entity);
  }
  
  /**
  * 更新分类名称
  * @return
  */
  @RequestMapping(value = "update")
  @ResponseBody
  public boolean update(TreeMenu entity) {
    // 获取当前用户id
    String userId = "46171f3814384f72b8284a43b25cb64f";
    entity.setUpdateUser(userId);
    return  treeMenuService.saveOrUpdate(entity);
  }

}
