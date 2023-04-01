package com.litongjava.spring.boot.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.litongjava.spring.boot.mybatis.model.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Ping E Lee
 * @since 2022-09-22
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
  
  public List<User> selectUser(@Param("name")String name,@Param("age")Integer age);
  
}
