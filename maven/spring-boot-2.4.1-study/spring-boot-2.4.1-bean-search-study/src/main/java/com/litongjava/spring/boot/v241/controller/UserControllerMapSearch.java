package com.litongjava.spring.boot.v241.controller;

import com.ejlchina.searcher.MapSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.litongjava.spring.boot.v241.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by litonglinux@qq.com on 2023/5/7_10:09
 */
@RestController
@RequestMapping("user/map/search")
public class UserControllerMapSearch {
  @Autowired
  private MapSearcher mapSearcher;  // 注入检索器（由 bean-searcher-boot-starter 提供）

  @GetMapping("/index")
  public SearchResult<Map<String, Object>> index(HttpServletRequest request) {
    Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
    return mapSearcher.search(User.class, flat);
  }
}
