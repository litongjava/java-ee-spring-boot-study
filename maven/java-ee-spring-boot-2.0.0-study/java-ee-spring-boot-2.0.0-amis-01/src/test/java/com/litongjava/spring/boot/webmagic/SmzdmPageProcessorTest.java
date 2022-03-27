package com.litongjava.spring.boot.webmagic;

import com.litongjava.spring.boot.SpiderApplication;
import com.litongjava.spring.boot.services.SmzdmService;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import us.codecraft.webmagic.Spider;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpiderApplication.class)
public class SmzdmPageProcessorTest {

  @Test
  public void test() {
    MysqlPipeline pipeline = new MysqlPipeline();
    SmzdmPageProcessor pageProcessor = new SmzdmPageProcessor();
    String url = "https://search.smzdm.com/?c=faxian&s=GU&v=b&p=1";
    Spider.create(pageProcessor)
        .addUrl(url)
        // 设置使用pipeline
        .addPipeline(pipeline).run();
  }

}
