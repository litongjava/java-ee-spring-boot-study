package com.litongjava.spring.boot.mybatis.service.impl;

import com.litongjava.spring.boot.mybatis.model.User;
import com.litongjava.spring.boot.mybatis.mapper.UserMapper;
import com.litongjava.spring.boot.mybatis.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ping E Lee
 * @since 2022-09-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
