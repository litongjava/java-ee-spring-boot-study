package com.alit.seralizable.servlet;

import java.io.File;

import org.springframework.core.env.Environment;

import com.alit.seralizable.config.ApplicationContextAwareImpl;

public class ResourcesStaticUtil {
  private static String resourcesStaticDir = null;
  private static File file = null;
  /**
   * 获取静态文件目录
   */
  public static String getResourcesStaticPath() {
    if (resourcesStaticDir == null) {
      Environment e = ApplicationContextAwareImpl.getBean(Environment.class);
      String property = e.getProperty("spring.resources.static-locations");
      System.out.println("spring.resources.static-locations:" + property);
      String[] split = property.split(":");
      if (split[0].equals("classpath")) {
        resourcesStaticDir = ResourcesStaticUtil.class.getClassLoader().getResource("").getFile() + split[1];
      } else if (split[0].equals("file")) {
        resourcesStaticDir = split[1];
      }
      file = new File(resourcesStaticDir);
      System.out.println("静态资源文件目录是:" + file.getAbsolutePath());
    }
    return resourcesStaticDir;
  }
}
