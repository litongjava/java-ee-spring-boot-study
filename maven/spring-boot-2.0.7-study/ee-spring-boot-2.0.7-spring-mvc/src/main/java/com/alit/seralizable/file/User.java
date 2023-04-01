package com.alit.seralizable.file;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
  private static final long serialVersionUID = 3103317843148814898L;
  private String id;
  private int age;
  private String name;
}