package com.alit.seralizable.rmi;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alit.seralizable.file.User;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring-mvc.xml")
public class UserServiceImplTest {
  public static void main(String[] args) {
    @SuppressWarnings("resource")
    ApplicationContext ac = new ClassPathXmlApplicationContext("client-application/applicationContext-httpInvoker.xml");
    UserService us = (UserService) ac.getBean("httpInvokerUserService");
    List<User> users = us.geAlltUsers();
    System.out.println(users);
  }
}