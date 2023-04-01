package top.ppnt.actable.gen;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author zhangpb
 * @date 2021/12/15 14:14
 * @Description:
 */
public class MysqlGenerator {

  /**
   * 每次只生成一张表的
   * @param args
   */
  public static void main(String[] args) {
    String jdbcDriver = "com.mysql.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost/mybatis_plus_study?userSSL=true&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
    String jdbcUser = "root";
    String jdbcPswd = "robot_123456#";
    
    // 代码生成器
    AutoGenerator autoGenerator = new AutoGenerator();

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    // String projectPath = System.getProperty("user.dir");
    // gc.setOutputDir(projectPath + "/src/main/java");
    String projectPath = "D:\\code\\java\\java-study\\java-ee-spring-boot-study\\maven\\java-ee-spring-boot-2.4.1-study\\java-ee-spring-boot-2.4.1-mybatis-enhance-actabler";
    gc.setOutputDir(projectPath + "\\src\\main\\java\\top\\ppnt\\actable");

    // TODO 设置用户名
    gc.setAuthor("zhangpb");
    gc.setOpen(true);
    // service 命名方式
    gc.setServiceName("%sService");
    // service impl 命名方式
    gc.setServiceImplName("%sServiceImpl");
    // 自定义文件命名，注意 %s 会自动填充表实体属性！
    gc.setMapperName("%sMapper");
    gc.setXmlName("%sMapper");
    gc.setFileOverride(true);
    gc.setActiveRecord(true);
    // XML 二级缓存
    gc.setEnableCache(false);
    // XML ResultMap
    gc.setBaseResultMap(true);
    // XML columList
    gc.setBaseColumnList(false);
    autoGenerator.setGlobalConfig(gc);

    // TODO 数据源配置
    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    dataSourceConfig.setDbType(DbType.MYSQL).setDriverName(jdbcDriver).setUrl(jdbcUrl).setUsername(jdbcUser)
    .setPassword(jdbcPswd);
    
    autoGenerator.setDataSource(dataSourceConfig);

    // TODO 包配置
    PackageConfig pc = new PackageConfig();
    // pc.setModuleName(scanner("模块名"));
    // pc.setParent("com.zhangpb.demodruid");
    pc.setEntity("entity");
    pc.setService("service");
    pc.setServiceImpl("service.impl");
    autoGenerator.setPackageInfo(pc);

    // 自定义需要填充的字段
    List<TableFill> tableFillList = new ArrayList<>();
    // 如 每张表都有一个创建时间、修改时间
    // 而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
    // 修改时，修改时间会修改，
    // 虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
    // 使用公共字段填充功能，就可以实现，自动按场景更新了。
    // 如下是配置
    // TableFill createField = new TableFill("gmt_create", FieldFill.INSERT);
    // TableFill modifiedField = new TableFill("gmt_modified",
    // FieldFill.INSERT_UPDATE);
    // tableFillList.add(createField);
    // tableFillList.add(modifiedField);

    // 自定义配置
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        // to do nothing
      }
    };
    List<FileOutConfig> focList = new ArrayList<>();
    focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        // 自定义输入文件名称
        return projectPath + "/src/main/resources/mapper/" + "/" + tableInfo.getEntityName() + "Mapper" + ".xml";
      }
    });
    cfg.setFileOutConfigList(focList);
    autoGenerator.setCfg(cfg);
    autoGenerator.setTemplate(new TemplateConfig().setXml(null));

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    strategy.setEntityLombokModel(true);
    // 设置逻辑删除键
    strategy.setLogicDeleteFieldName("deleted");
    // 指定生成的bean的数据库表名
    //strategy.setInclude("trade_coupon");
    // strategy.setSuperEntityColumns("id");
    // 驼峰转连字符
    strategy.setControllerMappingHyphenStyle(true);
    autoGenerator.setStrategy(strategy);
    // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
    //autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
    
    autoGenerator.execute();
  }
}