package com.litong.spring.boot.mybatis.tk;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration;
import tk.mybatis.spring.annotation.MapperScan;
@Import({DataSourceAutoConfiguration.class,MapperAutoConfiguration.class})
@MapperScan("com.litong.spring.boot.mybatis.tk.mapper")
public class ImportTkMybatis {
}
