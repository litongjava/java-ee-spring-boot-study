package com.litongjava.spring.boot.v241.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapBuilder;
import com.ejlchina.searcher.util.MapUtils;
import com.litongjava.spring.boot.v241.bean.User;

@RestController
@RequestMapping("/user")
public class UserController {

  // 注入 BeanSearcher 的检索器
  @Autowired
  private BeanSearcher beanSearcher;

  @GetMapping("/index")
  public SearchResult<User> index(HttpServletRequest request) {
    Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
    SearchResult<User> searchResult = beanSearcher.search(User.class, flat, new String[]{"age"});
    return searchResult;
  }

  @GetMapping("/index2")
  public List<User> bs(@RequestParam Map<String, Object> params) {
    return beanSearcher.searchList(User.class, params);
  }

  @RequestMapping("searchList")
  public List<User> searchList() {
    MapBuilder builder = MapUtils.builder();
    // 排除 joinDate 字段
    builder.selectExclude(User::getJoinDate)
      // 过滤：status = 0
      .field(User::getStatus, 0)
      // 过滤：name = 'admin' (ignored case)
      .field(User::getUsername, "admin").ic().
      // 过滤：age between 20 and= 30
        field(User::getAge, 19, 30).op("between")
      // 排序：年龄，从小到大
      .orderBy(User::getAge, "asc")
      // 分页：第 0 页, 每页 15 条
      .page(0, 15);

    Map<String, Object> params = builder.build();
    List<User> users = beanSearcher.searchList(User.class, params);
    return users;
  }
}
