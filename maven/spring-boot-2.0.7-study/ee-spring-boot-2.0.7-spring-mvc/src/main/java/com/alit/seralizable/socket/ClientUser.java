package com.alit.seralizable.socket;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClientUser implements Serializable {
  private static final long serialVersionUID = 3103317843148814898L;
  private int age;
  private String name;
}