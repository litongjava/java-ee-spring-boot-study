package top.ppnt.study.spring.boot.elasticsearch.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litongjava.utils.vo.PageJsonBean;

import lombok.extern.slf4j.Slf4j;
import top.ppnt.study.spring.boot.elasticsearch.StudentService;
import top.ppnt.study.spring.boot.elasticsearch.model.User;

@RestController
@RequestMapping("student")
@Slf4j
public class StudentSearchController {

  // 索引名称
  @Autowired
  private StudentService studentService;

  @GetMapping("mohues")
  public PageJsonBean<User> mohues(
      //
      @RequestParam(value = "keyword", defaultValue = "") String keyword,
      //
      @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
      //
      @RequestParam(value = "pageSize", defaultValue = "3") int pageSize) throws IOException {
    log.info("keyword:{}", keyword);
    return studentService.mohues(keyword, pageNo, pageSize);
  }
}
