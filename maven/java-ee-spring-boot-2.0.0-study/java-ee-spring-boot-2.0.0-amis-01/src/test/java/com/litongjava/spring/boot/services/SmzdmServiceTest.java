package com.litongjava.spring.boot.services;

import com.litongjava.spring.boot.SpiderApplication;
import com.litongjava.spring.boot.model.SmzdmModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by litonglinux@qq.com on 2022/1/17_22:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpiderApplication.class)
@Slf4j
public class SmzdmServiceTest {

  @Autowired
  private SmzdmService smzdmService;

  @Test
  public void testSave(){
    SmzdmModel model=new SmzdmModel();
    model.setTitle("GU 极优 男装长绒摇粒绒拼接茄克2021秋冬新款工装外套336147");
    model.setIntroduce("天猫精选此款目前活动售价129元，近期好价，感兴趣的值友可以入手。");
    model.setZhi("0");
    model.setNoZhi("0");
    model.setFbtime("更新时间：01-10 08:25");
    model.setUrl("https://go.smzdm.com/ae8fdfe49bfb484b/ca_aa_fx_57_46058787_974_32043_59_0");
    model.setImgurl("https://qny.smzdm.com/202201/10/61db7ca813d0c5986.jpg_d250.jpg");
    smzdmService.save(model);
    log.info("保存数据成功");
  }
  @Test
  public void selectAll() {
    List<SmzdmModel> smzdmModels = smzdmService.selectAll();
    System.out.println(smzdmModels.size());
  }

}
