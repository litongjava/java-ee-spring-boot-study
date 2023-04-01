package com.litongjava.spring.boot.sharding.jdbc.gen;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class Generator {
  public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/mybatis_plus_study";
    String username = "root";
    String password = "123456";
    String author = "author";
    String parent = "com.litongjava.spring.boot.sharding.jdbc";
    String moduleName = "user";
    String include = "user";
    FastAutoGenerator.create(url, username, password)
        //
        .globalConfig(
            //
            builder -> {
              builder.author(author).outputDir(System.getProperty("user.dir") + "/src/main/java").build();
            })
        .packageConfig(
            //
            builder -> {
              builder.parent(parent).moduleName(moduleName).build();
            })
        .strategyConfig(
            //
            builder -> {
              builder.addInclude(include).entityBuilder().enableLombok().disableSerialVersionUID().build();
            })
        .templateEngine(
            //
            new FreemarkerTemplateEngine())
        //
        .execute();
  }
}