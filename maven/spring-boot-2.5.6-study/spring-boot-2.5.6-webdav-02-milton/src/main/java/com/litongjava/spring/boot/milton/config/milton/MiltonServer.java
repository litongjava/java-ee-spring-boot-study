package com.litongjava.spring.boot.milton.config.milton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.litongjava.spring.boot.milton.config.SpringMiltonFilterBean;

/**
 * @author create by Ping E Lee on 2022年8月29日 下午10:04:14 
 *
 */
public class MiltonServer {

  /**
   * 初始化IP白名单
   */
  public static void initIpWhitelist() {
    ensureFileExist();

    if (IpWhitelist.cache.isEmpty()) {
      synchronized (SpringMiltonFilterBean.class) {
        if (IpWhitelist.cache.isEmpty()) {
          File file = new File("ip_whitelist");
          try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.lines().forEach(e -> IpWhitelist.cache.add(e));
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  private static void ensureFileExist() {
    File file = new File("ip_whitelist");
    if (!file.exists()) {
      try (FileWriter fw = new FileWriter(file)) {
        fw.append("ip:\r\n");
        fw.flush();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

}
