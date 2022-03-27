package com.litong.study.spring.boot.v158.excel.service.impl;

import com.litong.study.spring.boot.v158.excel.entity.User;
import com.litong.study.spring.boot.v158.excel.dao.UserMapper;
import com.litong.study.spring.boot.v158.excel.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litong
 * @since 2020-06-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
