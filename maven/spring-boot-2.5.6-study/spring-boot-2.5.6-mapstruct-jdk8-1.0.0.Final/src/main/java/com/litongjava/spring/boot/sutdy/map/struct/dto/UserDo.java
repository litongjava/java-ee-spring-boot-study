package com.litongjava.spring.boot.sutdy.map.struct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDo {

  private String username;
  private String password;
  private String iconUrl;

}