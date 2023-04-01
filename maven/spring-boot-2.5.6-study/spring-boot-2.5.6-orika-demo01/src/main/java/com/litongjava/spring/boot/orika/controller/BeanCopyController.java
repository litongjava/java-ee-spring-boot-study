package com.litongjava.spring.boot.orika.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litongjava.spring.boot.orika.model.Person;
import com.litongjava.spring.boot.orika.model.Student;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;

@RestController
public class BeanCopyController {
  @Autowired
  public MapperFactory mapperFactory;

  /**
   * @Decription 测试MapperFactory是否生成
   */
  @RequestMapping("/createMapperFactory")
  public String createMapperFactoryTest() {
    System.out.println("注入的mapperFactory是：" + mapperFactory);
    return "注入的mapperFactory是:" + mapperFactory;
  }

  /**
     * @throws java.text.ParseException 
   * @Decription 将一个已经存在的类的属性映射到另外一个类上（可以不存在），直接返回该类，注意
     * 必须要有默认的构造方法，不然会报错
     */
  @RequestMapping("/beanCopyToBean")
  public Student beanCopyToBean() throws java.text.ParseException {
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-29");

    Student student = null;

    Person person = new Person("lmh", 21, date);
    // MapperFactory 映射两个对象之间的属性、注册转换器、自定义映射器等等
    mapperFactory.classMap(Person.class, Student.class).field("dateTime", "birth") // 不同属性名的映射
        .byDefault() // 剩余字段（相同属性名）的映射
        .register(); // 向MapperFactory注册映射关系

    // 如果所有的字段都一样，就不用写mapperFactory.classMap()方法；
    MapperFacade mapperFacade = mapperFactory.getMapperFacade();
    student = mapperFacade.map(person, Student.class);
    System.out.println("属性赋值之后student的属性：" + student);
    return student;
  }

  /**
   * @Decription  将一个list映射到另一个list中
   */
  @RequestMapping("/beanCopyToList")
  public List<Student> beanCopyToList() {
    List<Person> personList = this.getPersonList();
    // 手动配置不一样属性转换
    mapperFactory.classMap(Person.class, Student.class)
        // 不一样字段的映射
        .field("dateTime", "birth")
        // 剩余字段的映射
        .byDefault()
        // 想MapperFacade注册映射关系
        .register();
    // 转换list
    List<Student> studentList = mapperFactory.getMapperFacade().mapAsList(personList, Student.class);
    studentList.forEach(student -> {
      System.out.println(student);
    });
    return studentList;
  }

  /**
   * @Decription 生成personList
   */
  public List<Person> getPersonList() {
    List<Person> list = new ArrayList<>(5);
    Person person1 = new Person("lmh1", 20, new Date());
    Person person2 = new Person("lmh2", 22, new Date());
    Person person3 = new Person("lmh3", 30, new Date());
    Person person4 = new Person("lmh4", 14, new Date());
    Person person5 = new Person("lmh5", 15, new Date());
    list.add(person1);
    list.add(person2);
    list.add(person3);
    list.add(person4);
    list.add(person5);
    return list;
  }
}