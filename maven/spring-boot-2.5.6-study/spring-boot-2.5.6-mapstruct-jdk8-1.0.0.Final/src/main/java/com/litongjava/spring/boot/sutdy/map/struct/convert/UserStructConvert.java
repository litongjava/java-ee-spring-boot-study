package com.litongjava.spring.boot.sutdy.map.struct.convert;

import org.mapstruct.Mapper;

import com.litongjava.spring.boot.sutdy.map.struct.dto.UserDo;
import com.litongjava.spring.boot.sutdy.map.struct.vo.UserVo;

@Mapper(componentModel = "spring")
public interface UserStructConvert {
  public UserVo toUserVo(UserDo model);
  public UserDo toUserDo(UserVo model);

}