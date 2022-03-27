package com.uairobot.bill.study.spring.boot.shiro.service;

import com.uairobot.bill.study.spring.boot.shiro.bean.User;

public interface LoginService {

  User getUserByName(String name);

}
