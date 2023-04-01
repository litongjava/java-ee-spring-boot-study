package com.litong.study.spring.boot.mybatis.plus.one;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusAutoConfiguration;

@Import({ DataSourceAutoConfiguration.class, MybatisPlusAutoConfiguration.class })
@MapperScan("com.litong.study.spring.boot.mybatis.plus.one.mapper")
public class AppOnlyDao {}
