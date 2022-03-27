package com.litong.spring.boot.v158.layui.v255;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;

/**
 * @author bill robot
 * @date 2020年5月28日_上午11:43:40 
 * @version 1.0 
 * @desc
 */
@Import({DataSourceAutoConfiguration.class,MybatisPlusAutoConfiguration.class})
@MapperScan("com.litong.spring.boot.v158.layui.v255.dao")
public class ImprotDao {

}
