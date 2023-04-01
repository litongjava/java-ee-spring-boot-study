package com.litongjava.spring.boot.milton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.stream.Stream;

import org.apache.mina.util.ConcurrentHashSet;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author create by Ping E Lee on 2022年8月29日 下午9:25:31 
 *
 */
@Slf4j
public class ApplicationTest {

  public static volatile ConcurrentHashSet<String> cache = new ConcurrentHashSet<>();

  @Test
  public void testReadFile() {
    File file = new File("ip_whitelist");
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      // 读取文件所有行
      Stream<String> lines = br.lines();
      // 使用foreach存入cache
      lines.forEach(e -> cache.add(e));
    } catch (Exception e) {
      e.printStackTrace();
    }

    // 遍历输出1
    cache.forEach(e -> System.out.println(e));
    // 遍历输出2
    cache.forEach(System.out::println);
    // 遍历方式3
    cache.forEach(log::info);
  }

}
