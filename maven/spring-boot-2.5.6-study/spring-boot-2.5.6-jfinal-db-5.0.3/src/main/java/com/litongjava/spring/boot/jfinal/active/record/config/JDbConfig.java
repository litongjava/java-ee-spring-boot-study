package com.litongjava.spring.boot.jfinal.active.record.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;
import com.litongjava.spring.boot.jfinal.active.record.utils.ConfigUtls;

@Configuration
public class JDbConfig {
  @Autowired
  private DataSource ds;

  @Primary
  @Bean(destroyMethod = "stop", initMethod = "start")
  public ActiveRecordPlugin activeRecordPlugin() throws Exception {
    ActiveRecordPlugin arp = new ActiveRecordPlugin(ds);
    arp.setDevMode(ConfigUtls.isDev());
    Engine engine = arp.getEngine();
    engine.setSourceFactory(new ClassPathSourceFactory());
    engine.setCompressorOn(' ');
    engine.setCompressorOn('\n');
    arp.addSqlTemplate("/sql/all_sqls.sql");
    return arp;
  }
}