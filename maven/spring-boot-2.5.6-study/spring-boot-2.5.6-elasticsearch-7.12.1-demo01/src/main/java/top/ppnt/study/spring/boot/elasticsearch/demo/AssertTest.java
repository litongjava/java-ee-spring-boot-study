package top.ppnt.study.spring.boot.elasticsearch.demo;

import com.litongjava.utils.string.StringUtils;
public class AssertTest {
  public static void main(String[] args) {
    String s = "Ping";
    assert StringUtils.isNotEmpty(s);
    System.out.println("success");
  }
}
