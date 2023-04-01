package com.litongjava.spring.boot.mybatis.plus.h2;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import({DataSourceAutoConfiguration.class, MybatisPlusAutoConfiguration.class})
@MapperScan("com.litongjava.spring.boot.mybatis.plus.h2.dao")
public class ImprotMp {
}