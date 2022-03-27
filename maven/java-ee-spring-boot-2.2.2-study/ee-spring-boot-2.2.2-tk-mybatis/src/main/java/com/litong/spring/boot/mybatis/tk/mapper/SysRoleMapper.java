package com.litong.spring.boot.mybatis.tk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.litong.spring.boot.mybatis.tk.model.SysRole;

import tk.mybatis.mapper.common.Mapper;

public interface SysRoleMapper extends Mapper<SysRole> {
  @Select("select r.id,r.role_name roleName ,r.role_desc roleDesc " + "FROM sys_role r,sys_user_role ur "
      + "WHERE r.id=ur.rid AND ur.uid=#{uid}")
  public List<SysRole> findByUid(Integer uid);
}
