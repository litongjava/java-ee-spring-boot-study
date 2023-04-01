package snippet;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Snippet {
  public static void main(String[] args) {
    long l = System.currentTimeMillis()+6*60*60*1000;
    System.out.println(l);
    Instant instant = Instant.ofEpochMilli(l);
    ZoneId zone = ZoneId.systemDefault();
    LocalDateTime expireTime=LocalDateTime.ofInstant(instant, zone);
    System.out.println(expireTime);
  }
}

