package com.example.springboot_security_jsp.service;

import com.example.springboot_security_jsp.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

/**
 * @author john
 * @date 2020/1/11 - 20:31
 */
public interface UserService extends UserDetailsService {
    public void save(SysUser user);

    public List<SysUser> findAll();

    public Map<String, Object> toAddRolePage(Integer id);

    public void addRoleToUser(Integer userId, Integer[] ids);
}
