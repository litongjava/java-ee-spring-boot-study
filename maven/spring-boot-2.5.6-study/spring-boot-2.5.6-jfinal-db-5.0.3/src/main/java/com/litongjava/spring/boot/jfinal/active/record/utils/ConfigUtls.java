package com.litongjava.spring.boot.jfinal.active.record.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author litongjava <litongjava@qq.com>
 *
 */
public class ConfigUtls {
  public static Properties properties;
  static {
    try {
      properties = PropertiesLoaderUtils.loadAllProperties("config.properties");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String getValue(String key) {
    return  properties.getProperty(key);
  }

  public static boolean isDev() {
    return "dev".equals(properties.getProperty("mode"));
  }
}
