package com.litong.study.spring.boot.v158.excel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;

/**
 * @author bill robot
 * @date 2020年6月4日_上午8:21:56 
 * @version 1.0 
 * @desc
 */
@Import({DataSourceAutoConfiguration.class,MybatisPlusAutoConfiguration.class})
@MapperScan("com.litong.study.spring.boot.v158.excel.dao")
@ComponentScan("com.litong.study.spring.boot.v158.excel.service")
public class ImportMp {}
