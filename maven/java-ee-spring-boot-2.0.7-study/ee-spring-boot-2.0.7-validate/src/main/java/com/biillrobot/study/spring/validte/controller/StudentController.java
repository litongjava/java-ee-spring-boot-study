package com.biillrobot.study.spring.validte.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biillrobot.study.spring.validte.dataobject.Student;

@RestController
@RequestMapping("/stu")
public class StudentController {

  @RequestMapping("add")
  public Student add(@Valid Student stu) {
    return stu;
  }
}
