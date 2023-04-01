package com.litongjava.spring.boot.orika.model;
import java.util.Date;

public class Student {
  private String name;
  private String grade;
  private Integer age;
  private Date birth;

  public Date getBirth() {
    return birth;
  }

  public Student setBirth(Date birth) {
    this.birth = birth;
    return this;
  }

  public String getName() {
    return name;
  }

  public Student setName(String name) {
    this.name = name;
    return this;
  }

  public String getGrade() {
    return grade;
  }

  public Student setGrade(String grade) {
    this.grade = grade;
    return this;
  }

  public Integer getAge() {
    return age;
  }

  public Student setAge(Integer age) {
    this.age = age;
    return this;
  }

  @Override
  public String toString() {
    return "Student{" + "name='" + name + '\'' + ", grade='" + grade + '\'' + ", age=" + age + ", birth=" + birth + '}';
  }
}