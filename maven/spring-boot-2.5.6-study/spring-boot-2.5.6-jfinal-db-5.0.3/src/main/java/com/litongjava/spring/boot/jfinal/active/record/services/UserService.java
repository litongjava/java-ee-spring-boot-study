package com.litongjava.spring.boot.jfinal.active.record.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jfinal.plugin.activerecord.Record;
import com.litongjava.spring.boot.jfinal.active.record.dao.UserDao;
import com.litongjava.spring.boot.jfinal.active.record.model.User;

/**
 * @author litongjava <litongjava@qq.com>
 *
 */
@Service
public class UserService {

  @Autowired
  private UserDao userDao;

  public List<Record> list() {
    return userDao.list();
  }

  public List<Map<String, Object>> listForMap() {
    List<Map<String, Object>> retval = new ArrayList<>();
    List<Record> listRecord = userDao.list();
    for (int i = 0; i < listRecord.size(); i++) {
      Record record = listRecord.get(i);
      Map<String, Object> map = record.toMap();
      retval.add(map);
    }
    return retval;
  }

  public List<Map<String, Object>> listForMap2() {
    return userDao.list().stream().map(e -> e.toMap()).collect(Collectors.toList());
  }

  public List<User> listForModel() {
    return userDao.listForModel();
  }

  public List<User> listFromTemplate() {
    return userDao.listFromTemplate();
  }
}
