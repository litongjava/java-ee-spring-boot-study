package com.litongjava.spring.boot.mybatis.controller;

import static org.apache.ibatis.type.JdbcType.VARCHAR;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;

/**
 * Mybatis - 获取Mybatis查询sql工具
 */
public class MybatisSqlHelper {

  /**
   * 通过命名空间方式获取sql
   * @param sqlSessionFactory
   * @param namespace
   * @param params
   * @return
   */
  public static String getNamespaceSql(SqlSessionFactory sqlSessionFactory, String namespace, Object params) {
    params = wrapCollection(params);
    Configuration configuration = sqlSessionFactory.getConfiguration();
    MappedStatement mappedStatement = configuration.getMappedStatement(namespace);
    TypeHandlerRegistry typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
    BoundSql boundSql = mappedStatement.getBoundSql(params);
    List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
    StringBuilder sqlStringBuilder = new StringBuilder(boundSql.getSql().replaceAll("\n", ""));
    if (params != null && parameterMappings != null && parameterMappings.size() > 0) {
      for (ParameterMapping parameterMapping : parameterMappings) {
        if (parameterMapping.getMode() != ParameterMode.OUT) {
          Object value;
          String propertyName = parameterMapping.getProperty();
          if (boundSql.hasAdditionalParameter(propertyName)) {
            value = boundSql.getAdditionalParameter(propertyName);
          } else if (typeHandlerRegistry.hasTypeHandler(params.getClass())) {
            value = params;
          } else {
            MetaObject metaObject = configuration.newMetaObject(params);
            value = metaObject.getValue(propertyName);
          }
          JdbcType jdbcType = parameterMapping.getJdbcType();
          if (jdbcType == null) {
            jdbcType = VARCHAR;
          }
          replaceParameter(sqlStringBuilder, value, jdbcType);
        }
      }
    }
    return sqlStringBuilder.toString();
  }

  /**
   * 根据类型替换参数
   * 仅作为数字和字符串两种类型进行处理，需要特殊处理的可以继续完善这里
   *
   * @param sqlStringBuilder
   * @param value
   * @param jdbcType
   * @return
   */
  private static void replaceParameter(StringBuilder sqlStringBuilder, Object value, JdbcType jdbcType) {
    if (value == null) {
      return;
    }

    String strValue = String.valueOf(value);
    switch (jdbcType) {
    // 数字
    case BIT:
      break;
    case TINYINT:
      break;
    case SMALLINT:
      break;
    case INTEGER:
      break;
    case BIGINT:
      break;
    case FLOAT:
      break;
    case REAL:
      break;
    case DOUBLE:
      break;
    case NUMERIC:
      break;
    case DECIMAL:
      break;
    // 其他，包含字符串和其他特殊类型，加单引号
    default:
      strValue = "'" + strValue + "'";
    }
    int index = sqlStringBuilder.indexOf("?");
    sqlStringBuilder.replace(index, index + 1, strValue);
  }

  /**
   * 简单包装参数
   *
   * @param object
   * @return
   */
  private static Object wrapCollection(final Object object) {
    if (object instanceof List) {
      Map<String, Object> map = new HashMap<>();
      map.put("list", object);
      return map;
    } else if (object != null && object.getClass().isArray()) {
      Map<String, Object> map = new HashMap<>();
      map.put("array", object);
      return map;
    }
    return object;
  }
}
