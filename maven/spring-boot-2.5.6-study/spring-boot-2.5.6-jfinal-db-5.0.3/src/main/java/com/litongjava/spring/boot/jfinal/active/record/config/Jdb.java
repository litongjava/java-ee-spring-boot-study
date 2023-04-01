package com.litongjava.spring.boot.jfinal.active.record.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbTemplate;
import com.jfinal.plugin.activerecord.Record;
import com.litongjava.spring.boot.jfinal.active.record.model.User;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;

@Component
public class Jdb extends Db {

  @Autowired
  public MapperFactory mapperFactory;

  public <T> List<T> findList(String sql, Class<T> clazz) {
    List<Record> records = Jdb.find(sql);
    return toListModel(records,clazz);
  }

  /**
   * 转为model类型
   */
  public <T> List<T> toListModel(List<Record> records,Class<T> clazz) {
    // 必须转为Map
    List<Map<String, Object>> collect = records.stream().map(e -> e.toMap()).collect(Collectors.toList());
    MapperFacade mapperFacade = mapperFactory.getMapperFacade();
    List<T> mapAsList = mapperFacade.mapAsList(collect, clazz);
    return mapAsList;
  }

  public <T> List<T> templateList(String sqlId,Class<T> clazz) {
    DbTemplate template = Jdb.template(sqlId);
    List<Record> records = template.find();
    return toListModel(records,clazz);
  }
}