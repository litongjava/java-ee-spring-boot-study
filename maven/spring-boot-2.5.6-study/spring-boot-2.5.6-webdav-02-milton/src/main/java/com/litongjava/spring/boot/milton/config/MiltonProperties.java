package com.litongjava.spring.boot.milton.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "milton")
@Getter
@Setter
public class MiltonProperties {
  private List<String> excludePaths = new ArrayList<>();

  private String filesystemRoot;

  private String username;

  private String password;
  
}
