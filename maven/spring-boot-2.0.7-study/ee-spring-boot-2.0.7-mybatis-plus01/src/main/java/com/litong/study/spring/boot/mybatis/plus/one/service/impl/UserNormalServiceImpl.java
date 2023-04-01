package com.litong.study.spring.boot.mybatis.plus.one.service.impl;

import com.litong.study.spring.boot.mybatis.plus.one.entity.UserNormal;
import com.litong.study.spring.boot.mybatis.plus.one.mapper.UserNormalMapper;
import com.litong.study.spring.boot.mybatis.plus.one.service.IUserNormalService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 普通用户 服务实现类
 * </p>
 *
 * @author litong
 * @since 2020-04-24
 */
@Service
public class UserNormalServiceImpl extends ServiceImpl<UserNormalMapper, UserNormal> implements IUserNormalService {

}
