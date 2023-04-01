package com.litong.study.spring.boot.v158.excel.gen;

import java.io.File;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyBatisPlusGenerator {

  private static String codePath = getCodePath();
  private static String jdbcDriver = "com.mysql.jdbc.Driver";
  private static String jdbcUrl = "jdbc:mysql://localhost:3306/study_mybatis_plus?useunicode=true&characterEncoding=utf8";
  private static String jdbcUser = "root";
  private static String jdbcPswd = "";
  private static String packageParentName = "com.litong.study.spring.boot.v158.excel";

  public static void main(String[] args) {

    // 全局配置
    GlobalConfig globalConfig = new GlobalConfig();
    globalConfig.setActiveRecord(false).setAuthor("litong").setOutputDir(codePath).setFileOverride(true)
        .setServiceName("%sService").setBaseResultMap(true).setBaseColumnList(true);
    // 数据源配置
    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    dataSourceConfig.setDbType(DbType.MYSQL).setDriverName(jdbcDriver).setUrl(jdbcUrl).setUsername(jdbcUser)
        .setPassword(jdbcPswd);
    // 策略配置
    StrategyConfig strategyConfig = new StrategyConfig();
    strategyConfig.setCapitalMode(true).setNaming(NamingStrategy.underline_to_camel).
    setInclude(new String[] { "user" });// 生成的表,可同时传入多个表名
    // 包名策略配置
    PackageConfig packageConfig = new PackageConfig();
    packageConfig.setParent(packageParentName).setMapper("dao").setService("service").setController("controller")
        .setEntity("entity").setXml("dao");

    // 整合配置
    AutoGenerator ag = new AutoGenerator();
    ag.setGlobalConfig(globalConfig).setDataSource(dataSourceConfig).setStrategy(strategyConfig).setPackageInfo(packageConfig);
    // 执行
    ag.execute();
  }

  private static String getCodePath() {
    String absolutePath = new File("").getAbsolutePath();
    return append(File.separator, absolutePath, "src", "main", "java");
  }

  private static String append(String separator, String... strs) {
    StringBuffer sb = new StringBuffer();
    for (String string : strs) {
      sb.append(string).append(separator);
    }
    return sb.toString();
  }

}