package com.uairobot.bill.study.mybatis.plus.service.impl;

import com.uairobot.bill.study.mybatis.plus.entity.User;
import com.uairobot.bill.study.mybatis.plus.dao.UserMapper;
import com.uairobot.bill.study.mybatis.plus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litong
 * @since 2019-12-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
