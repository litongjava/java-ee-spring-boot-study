package com.example.springboot_security_jsp.mapper;

import com.example.springboot_security_jsp.domain.SysUser;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author john
 * @date 2020/1/11 - 20:25
 */
public interface UserMapper extends Mapper<SysUser> {
  @Select("select * from sys_user where username=#{username}")
  @Results({ @Result(id = true, property = "id", column = "id"),
      @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "com.example.springboot_security_jsp.mapper.RoleMapper.findByUid")) })
  public SysUser findByUsername(String username);
}
