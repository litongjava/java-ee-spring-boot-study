package com.litongjava.spring.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.litongjava.spring.boot.model.SmzdmModel;
@Mapper
public interface SmzdmMapper extends BaseMapper<SmzdmModel> {
  @Select("select * from smzdm")
  List<SmzdmModel> selectAll();
}