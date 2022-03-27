package com.example.springboot_security_jsp.service.impl;

import com.example.springboot_security_jsp.domain.SysRole;
import com.example.springboot_security_jsp.domain.SysUser;
import com.example.springboot_security_jsp.mapper.UserDao;
import com.example.springboot_security_jsp.mapper.UserMapper;
import com.example.springboot_security_jsp.service.RoleService;
import com.example.springboot_security_jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author john
 * @date 2020/1/11 - 20:32
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserDao userDao;

  @Autowired
  private RoleService roleService;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public void save(SysUser user) {
    // 密码加密
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userDao.save(user);
  }

  public List<SysUser> findAll() {
    return userDao.findAll();
  }

  public Map<String, Object> toAddRolePage(Integer id) {
    List<SysRole> allRoles = roleService.findAll();
    List<Integer> myRoles = userDao.findRolesByUid(id);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("allRoles", allRoles);
    map.put("myRoles", myRoles);
    return map;
  }

  public void addRoleToUser(Integer userId, Integer[] ids) {
    userDao.removeRoles(userId);
    for (Integer rid : ids) {
      userDao.addRoles(userId, rid);
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userMapper.findByUsername(username);
  }
}
