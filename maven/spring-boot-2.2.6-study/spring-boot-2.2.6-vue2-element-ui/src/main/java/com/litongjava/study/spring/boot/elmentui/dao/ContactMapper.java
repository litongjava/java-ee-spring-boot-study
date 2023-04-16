package com.litongjava.study.spring.boot.elmentui.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.litongjava.study.spring.boot.elmentui.entity.Contact;

/**
 * <p>
 * 联系人 Mapper 接口
 * </p>
 *
 * @author litongjava
 * @since 2023-04-12
 */
@Mapper
public interface ContactMapper extends BaseMapper<Contact> {

}
