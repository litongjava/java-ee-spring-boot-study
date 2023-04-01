package com.example.springboot_security_jsp.service;



import com.example.springboot_security_jsp.domain.SysRole;

import java.util.List;

public interface RoleService {

    public void save(SysRole role);

    public List<SysRole> findAll();

}
