//package com.litongjava.spring.boot.beetl.sql.demo.controller;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.beetl.sql.core.SQLManager;
//import org.beetl.sql.core.SQLReady;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.litongjava.spring.boot.beetl.sql.demo.model.User;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author Ping E Lee
// *
// */
//@RestController
//@RequestMapping("user")
//@Slf4j
//public class UserController {
//
//  @Autowired
//  private SQLManager sqlManager;
//
//  @RequestMapping("example01")
//  public void example01() {
//    // 出现数据
//    User user = sqlManager.unique(User.class, 1);
//
//    user.setName("ok123");
//    // 更新数据
//    sqlManager.updateById(user);
//
//    // 插入数据
//    User newUser = new User();
//    newUser.setName("newUser");
//    // newUser.setDepartmentId(1);
//    sqlManager.insert(newUser);
//  }
//
//  @RequestMapping("example02")
//  public void example02() {
//    String sql = "select * from user where id=?";
//    Integer id = 1;
//    SQLReady sqlReady = new SQLReady(sql, id);
//    List<User> userEntities = sqlManager.execute(sqlReady, User.class);
//    log.info("userEntities:{}", userEntities);
//    // Map 也可以作为输入输出参数
//    List<Map> listMap = sqlManager.execute(sqlReady, Map.class);
//    log.info("listMap:{}", listMap);
//  }
//  
//  @RequestMapping("example03")
//  public void example03() {
////    //sql模板
////    String sql = "select * from user where department_id=#{id} and name=#{name}";
////    //sql参数
////    User paras = new User();
////    paras.setDepartmentId(1);
////    paras.setName("lijz");
////    //执行
////    List<User> userData = sqlManager.execute(sql,User.class,paras);
////    log.info("list:{}", userData);
//    
//    
//    String sql2 = "select * from user where id in ( #{join(ids)} )";
//    List list = Arrays.asList(1,2,3,4,5); 
//    Map mapParas = new HashMap();
//    mapParas.put("ids", list);
//    List<User> userData2 = sqlManager.execute(sql2, User.class, mapParas);
//    log.info("list:{}", userData2);
//  }
//}
