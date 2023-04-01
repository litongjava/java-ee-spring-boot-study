package com.example.springboot_security_jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.springboot_security_jsp.mapper")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringbootSecurityJspApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringbootSecurityJspApplication.class, args);
  }

}
