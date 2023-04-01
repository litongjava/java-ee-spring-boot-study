package snippet;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.litongjava.spring.boot.jfinal.active.record.model.SysUser;

public class Test01 {

  public static void main(String[] args) {
    new Test01().test01();
  }

  public void test02() {
    long l = System.currentTimeMillis() + 6 * 60 * 60 * 1000;
    System.out.println(l);
    Instant instant = Instant.ofEpochMilli(l);
    ZoneId zone = ZoneId.systemDefault();
    LocalDateTime expireTime = LocalDateTime.ofInstant(instant, zone);
    System.out.println(expireTime);
  }
  @Test
  public void test01() {
    List<SysUser> lists = new ArrayList<>();
    for (long i = 0L; i < 10; i++) {
      lists.add(new SysUser(i, i + "litong"));
    }
    System.out.println(lists);
    for (SysUser sysUser : lists) {
      sysUser.setSuername("lida");
    }
  }
}
