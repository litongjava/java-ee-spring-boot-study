package com.example.springboot_security_jsp.mapper;

import com.example.springboot_security_jsp.domain.SysRole;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<SysRole> {
  @Select("select r.id,r.role_name roleName ,r.role_desc roleDesc " + "FROM sys_role r,sys_user_role ur "
      + "WHERE r.id=ur.rid AND ur.uid=#{uid}")
  public List<SysRole> findByUid(Integer uid);
}
