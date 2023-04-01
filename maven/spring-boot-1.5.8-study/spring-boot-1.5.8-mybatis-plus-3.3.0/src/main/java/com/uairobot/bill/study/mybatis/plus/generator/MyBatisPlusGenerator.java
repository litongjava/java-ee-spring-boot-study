package com.uairobot.bill.study.mybatis.plus.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyBatisPlusGenerator {
  private static String projectPath = "D:\\dev_workspace\\java\\java-study\\ee-springboot-1.5.8-parent\\1.5.8-mybatis-plus\\src\\main\\java";
  private static String jdbcDriver = "com.mysql.jdbc.Driver";
  private static String jdbcUrl = "jdbc:mysql://localhost/study_mybatis_plus?userSSL=true&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
  private static String jdbcUser = "root";
  private static String jdbcPswd = "";
  private static String packageParentName = "com.uairobot.bill.study.mybatis.plus";

  public static void main(String[] args) {

    // 全局配置
    GlobalConfig globalConfig = new GlobalConfig();
    globalConfig.setActiveRecord(false).setAuthor("litong").setOutputDir(projectPath).setFileOverride(true)
        .setServiceName("%sService").setBaseResultMap(true).setBaseColumnList(true);
    // 数据源配置
    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    dataSourceConfig.setDbType(DbType.MYSQL).setDriverName(jdbcDriver).setUrl(jdbcUrl).setUsername(jdbcUser)
        .setPassword(jdbcPswd);
    // 策略配置
    StrategyConfig strategyConfig = new StrategyConfig();
    strategyConfig.setCapitalMode(true).setNaming(NamingStrategy.underline_to_camel)
        .setInclude(new String[] { "user" });// 生成的表,可同时传入多个表名
    // 包名策略配置
    PackageConfig packageConfig = new PackageConfig();
    packageConfig.setParent(packageParentName).setMapper("dao").setService("service").setController("controller")
        .setEntity("entity").setXml("mapper");

    // 整合配置
    AutoGenerator ag = new AutoGenerator();
    ag.setGlobalConfig(globalConfig).setDataSource(dataSourceConfig).setStrategy(strategyConfig)
        .setPackageInfo(packageConfig);
    // 执行
    ag.execute();
  }
}
