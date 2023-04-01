package top.ppnt.study.spring.boot.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Document(indexName = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {
  @Id
  private String id;

  private String name;

  private String gender;

  private Integer age;

  public Student(String name, String gender, int age) {
    this.name = name;
    this.gender = gender;
    this.age = age;
  }
}