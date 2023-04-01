package com.litongjava.spring.boot.sharding.jdbc.user.service.impl;

import com.litongjava.spring.boot.sharding.jdbc.user.entity.User;
import com.litongjava.spring.boot.sharding.jdbc.user.mapper.UserMapper;
import com.litongjava.spring.boot.sharding.jdbc.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2022-10-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
