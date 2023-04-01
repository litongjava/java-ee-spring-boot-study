package top.ppnt.study.spring.boot.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
  
  private String id;
  private String name;
  private int age;
  
  public User(String name, int age) {
    this.name=name;
    this.age=age;
  }
}