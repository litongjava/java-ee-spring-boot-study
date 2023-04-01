package com.litong.java.sprong.boot.tomcat.listener.model;

import java.util.HashMap;
import java.util.Map;

public class DataMap extends HashMap<String, Object> {

  private static final long serialVersionUID = -4661580543637148131L;

  public DataMap() {
  }

  public DataMap(Map<? extends String, ? extends Object> map) {
    super(map);
  }

  public Integer getInteger(String key) {
    Object value = super.get(key);
    return value == null ? null : Integer.parseInt(value.toString());
  }

  public Integer getInteger(String key, Integer defaultValue) {
    Integer value = getInteger(key);
    return value == null ? defaultValue : value;
  }

  public Long getLong(String key) {
    Object value = super.get(key);
    return value == null ? null : Long.parseLong(value.toString());
  }

  public Long getLong(String key, Long defaultValue) {
    Long value = getLong(key);
    return value == null ? defaultValue : value;
  }

  public Double getDouble(String key) {
    Object value = super.get(key);
    return value == null ? null : Double.parseDouble(value.toString());
  }

  public String getString(String key) {
    Object value = super.get(key);
    return value == null ? null : value.toString();
  }

  public <T> T getObject(String key) {
    Object value = super.get(key);
    return value == null ? null : (T) value;
  }

  public String getString(String key, String defaultValue) {
    Object value = super.get(key);
    return value == null ? defaultValue : value.toString();
  }
}
