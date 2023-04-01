package com.litongjava.spring.boot.mybatis.controller;

import java.util.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author litongjava <litongjava@qq.com>
 *
 */
@RestController
@RequestMapping("mybatis")
@Slf4j
public class MybatisController {

  @Autowired
  private SqlSessionFactory sqlSessionFactory;

  @RequestMapping("getMapperStatementSql")
  public String getMapperStatementSql() {

    //全类名+方法名
    String statementId = "com.litongjava.spring.boot.mybatis.mapper.UserMapper.selectUser";
    //参数
//    List<Object> params = new ArrayList<>();
//    Collections.addAll(params, "litong", 18);
    Map<String,Object> params=new HashMap<>();
    params.put("name","litong");
    params.put("age",18);

    String sql = MybatisSqlHelper.getNamespaceSql(sqlSessionFactory, statementId, params);
    log.info("sql=" + sql);
    return sql;
  }
}
