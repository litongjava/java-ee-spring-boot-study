package com.litongjava.spring.boot.milton.config.milton;

import org.apache.mina.util.ConcurrentHashSet;

public class IpWhitelist {
  public static volatile ConcurrentHashSet<String> cache = new ConcurrentHashSet<>();
}
