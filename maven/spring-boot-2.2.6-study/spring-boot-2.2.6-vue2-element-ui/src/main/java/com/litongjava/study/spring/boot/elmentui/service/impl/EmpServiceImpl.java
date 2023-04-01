package com.litongjava.study.spring.boot.elmentui.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.litongjava.study.spring.boot.elmentui.dao.EmpDao;
import com.litongjava.study.spring.boot.elmentui.entity.Emp;
import com.litongjava.study.spring.boot.elmentui.service.EmpService;

/**
 * (Emp)表服务实现类
 *
 * @author litongjava
 * @since 2023-04-01 18:12:16
 */
@Service("empService")
public class EmpServiceImpl implements EmpService {
  @Resource
  private EmpDao empDao;

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public Emp queryById(Integer id) {
    return this.empDao.queryById(id);
  }

  /**
   * 分页查询
   *
   * @param emp         筛选条件
   * @param pageRequest 分页对象
   * @return 查询结果
   */
  @Override
  public Page<Emp> queryByPage(Emp emp, PageRequest pageRequest) {
    long total = this.empDao.count(emp);
    return new PageImpl<>(this.empDao.queryAllByLimit(emp, pageRequest), pageRequest, total);
  }

  /**
   * 新增数据
   *
   * @param emp 实例对象
   * @return 实例对象
   */
  @Override
  public Emp insert(Emp emp) {
    this.empDao.insert(emp);
    return emp;
  }

  /**
   * 修改数据
   *
   * @param emp 实例对象
   * @return 实例对象
   */
  @Override
  public Emp update(Emp emp) {
    this.empDao.update(emp);
    return this.queryById(emp.getId());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Integer id) {
    return this.empDao.deleteById(id) > 0;
  }
}
