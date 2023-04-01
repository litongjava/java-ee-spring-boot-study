package com.litongjava.spring.boot.orika.model;
import java.util.Date;

public class Person {

  private String name;
  private Integer age;
  private Date dateTime;
  
  public Person() {
  }

  public Person(String name, Integer age, Date dateTime) {
    this.name = name;
    this.age = age;
    this.dateTime = dateTime;
  }


  public String getName() {
    return name;
  }

  public Person setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getAge() {
    return age;
  }

  public Person setAge(Integer age) {
    this.age = age;
    return this;
  }

  public Date getDateTime() {
    return dateTime;
  }

  public Person setDateTime(Date dateTime) {
    this.dateTime = dateTime;
    return this;
  }

  @Override
  public String toString() {
    return "Person{" + "name='" + name + '\'' + ", age=" + age + ", dateTime=" + dateTime + '}';
  }
}