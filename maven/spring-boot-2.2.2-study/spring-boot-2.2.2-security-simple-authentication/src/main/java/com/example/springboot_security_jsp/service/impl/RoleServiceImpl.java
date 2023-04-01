package com.example.springboot_security_jsp.service.impl;


import com.example.springboot_security_jsp.domain.SysRole;
import com.example.springboot_security_jsp.mapper.RoleDao;
import com.example.springboot_security_jsp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public void save(SysRole role) {
        roleDao.save(role);
    }

    @Override
    public List<SysRole> findAll() {
        return roleDao.findAll();
    }
}
