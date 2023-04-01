package com.litongjava.spring.boot.beetl.sql.demo.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import com.litongjava.spring.boot.beetl.sql.demo.model.User;

@SqlResource("user")
public interface UserDao extends BaseMapper<User> {
  List<User> findByName(String name);
}