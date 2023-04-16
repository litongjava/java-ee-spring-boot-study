package com.litongjava.study.spring.boot.elmentui.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litongjava.study.spring.boot.elmentui.dao.ContactMapper;
import com.litongjava.study.spring.boot.elmentui.entity.Contact;
import com.litongjava.study.spring.boot.elmentui.service.ContactService;

/**
 * <p>
 * 联系人 服务实现类
 * </p>
 *
 * @author litongjava
 * @since 2023-04-12
 */
@Service
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements ContactService {

}
