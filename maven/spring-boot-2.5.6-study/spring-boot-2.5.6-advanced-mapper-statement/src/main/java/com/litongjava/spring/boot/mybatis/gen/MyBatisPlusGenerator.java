package com.litongjava.spring.boot.mybatis.gen;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyBatisPlusGenerator {

  private static String codePath = getCodePath();

  public static void main(String[] args) {
    String author = "Ping E Lee";
    String packageParentName = "com.litongjava.spring.boot.mybatis";

    String jdbcDriver = "com.mysql.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost/mybatis_plus_study?userSSL=true&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
    String jdbcUser = "root";
    String jdbcPswd = "123456";
    String deleteField="is_del";
    execute(author, packageParentName, jdbcDriver, jdbcUrl, jdbcUser, jdbcPswd,deleteField);
  }

  public static void execute(String author, String packageParentName, String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPswd,String deleteField) {
    // 全局配置
    GlobalConfig globalConfig = new GlobalConfig();
    globalConfig
        // 设置作者
        .setAuthor(author)
        // 设置代码输出目录
        .setOutputDir(codePath)
        // service 命名方式
        .setServiceName("%sService")
        // 是否覆盖文件
        .setFileOverride(true)
        // 设置activeRecord
        .setActiveRecord(false)
        // XML ResultMap
        .setBaseResultMap(true)
        // XML columList
        .setBaseColumnList(true);

    // 数据源配置
    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    dataSourceConfig
        // 设置数据库类型
        .setDbType(DbType.MYSQL)
        // dirverName
        .setDriverName(jdbcDriver)
        // jdbcUrl,user,password
        .setUrl(jdbcUrl).setUsername(jdbcUser).setPassword(jdbcPswd);

    PackageConfig packageConfig = new PackageConfig();
    packageConfig
        // 设置父包名
        .setParent(packageParentName)
        // mapper包名
        .setMapper("mapper")
        // service包名
        .setService("service")
        // controllr包名
        .setController("controller")
        // 视图类包名
        .setEntity("model");
    // mapper文件包名
    // .setXml("mapper");

    // 自定义配置
    InjectionConfig injectionConfig = new InjectionConfig() {
      @Override
      public void initMap() {
        // to do nothing
      }
    };
    // 自定义 mapper xml保存路径
    List<FileOutConfig> focList = new ArrayList<>();
    focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        // 自定义输入文件名称
        return getProjectPath() + "/src/main/resources/mappers/" + "/" + tableInfo.getEntityName() + "Mapper" + ".xml";
      }
    });
    injectionConfig.setFileOutConfigList(focList);

    // 设置xml模板为null,这样才会使用自定义的xml模板
    TemplateConfig templateConfig = new TemplateConfig().setXml(null);

    // 策略配置
    StrategyConfig strategyConfig = new StrategyConfig();
    strategyConfig
        // 大写模式
        .setCapitalMode(true)
        // 设置类名 命名方式
        .setNaming(NamingStrategy.underline_to_camel)
        // 设置字段名 命名方式
        .setColumnNaming(NamingStrategy.underline_to_camel)
        // 使用lombok
        .setEntityLombokModel(true)
        // 驼峰转连字符
        .setControllerMappingHyphenStyle(true);

    // 设置逻辑删除键
    if(!StringUtils.isEmpty(deleteField)) {
      strategyConfig.setLogicDeleteFieldName(deleteField);
    }
    

    // 设置表前缀
    // strategyConfig.setTablePrefix("t_");
    // 设置生成的表
    // strategyConfig.setInclude(new String[] { "t_user_agreement" });//
    // 生成的表,可同时传入多个表名
    // 包名策略配置

    // 整合配置
    AutoGenerator autoGenerator = new AutoGenerator();
    autoGenerator
        // 设置全局配置
        .setGlobalConfig(globalConfig)
        // 设置数据库信息
        .setDataSource(dataSourceConfig)
        // 设置策略信息
        .setStrategy(strategyConfig)
        // 设置包信息
        .setPackageInfo(packageConfig);
    // 指定自定义配置
    autoGenerator.setCfg(injectionConfig);
    // 指定模板配置
    autoGenerator.setTemplate(templateConfig);
    // 执行
    autoGenerator.execute();
  }

  private static String getCodePath() {
    String absolutePath = getProjectPath();
    return append(File.separator, absolutePath, "src", "main", "java");
  }

  private static String getProjectPath() {
    String absolutePath = new File("").getAbsolutePath();
    return absolutePath;
  }

  private static String append(String separator, String... strs) {
    StringBuffer sb = new StringBuffer();
    for (String string : strs) {
      sb.append(string).append(separator);
    }
    return sb.toString();
  }
}