package top.ppnt.actable.service.impl;

import top.ppnt.actable.model.User;
import top.ppnt.actable.mapper.UserMapper;
import top.ppnt.actable.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ping E Lee
 * @since 2022-05-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
