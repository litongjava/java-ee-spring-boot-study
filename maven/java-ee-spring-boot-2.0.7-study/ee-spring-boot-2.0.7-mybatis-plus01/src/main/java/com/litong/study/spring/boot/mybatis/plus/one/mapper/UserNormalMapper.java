package com.litong.study.spring.boot.mybatis.plus.one.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.litong.study.spring.boot.mybatis.plus.one.entity.UserNormal;

/**
 * <p>
 * 普通用户 Mapper 接口
 * </p>
 *
 * @author litong
 * @since 2020-04-24
 */
public interface UserNormalMapper extends BaseMapper<UserNormal> {
  public List<String> getName(@Param("name") String name);
}
