package com.litong.spring.boot.mybatis.tk.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.litong.spring.boot.mybatis.tk.ImportTkMybatis;
import com.litong.spring.boot.mybatis.tk.model.SysRole;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ImportTkMybatis.class)
public class SysRoleMapperTest {

  @Autowired
  private SysRoleMapper sysRoleMapper;
  
  @Test
  public void insert() {
    SysRole sysRole = new SysRole();
    sysRole.setId(3);
    sysRole.setRoleName("project");
    sysRole.setRoleDesc("工程人员");
    sysRoleMapper.insert(sysRole);
  }

  @Test
  public void list() {
    List<SysRole> selectAll = sysRoleMapper.selectAll();
    System.out.println(selectAll);
  }
}
