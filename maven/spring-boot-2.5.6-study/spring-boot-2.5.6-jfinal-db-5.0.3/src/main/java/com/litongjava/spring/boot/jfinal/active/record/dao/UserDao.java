package com.litongjava.spring.boot.jfinal.active.record.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jfinal.plugin.activerecord.Record;
import com.litongjava.spring.boot.jfinal.active.record.config.Jdb;
import com.litongjava.spring.boot.jfinal.active.record.model.User;

/**
 * @author litongjava <litongjava@qq.com>
 *
 */
@Repository
public class UserDao {

  @Autowired
  private Jdb jdb;

  public List<Record> list() {
    List<Record> result = Jdb.find("select * from user");
    return result;
  }

  public List<User> listForModel() {
    return jdb.findList("select * from user", User.class);
  }
  
  public List<User> listFromTemplate(){
    return jdb.templateList("user.findList",User.class);
  }
}
