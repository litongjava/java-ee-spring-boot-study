package com.ppnt.study.mybatis.plus.model;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.ppnt.study.mybatis.plus.config.MyBatisPlusConfig;

/**
 * @author bill robot
 * @date 2020年5月26日_上午11:31:50 
 * @version 1.0 
 * @desc
 */
@Import({ DataSourceAutoConfiguration.class, MybatisPlusAutoConfiguration.class, MyBatisPlusConfig.class })
@MapperScan("com.uairobot.bill.study.mybatis.plus.dao")
public class ImportMp {

}
