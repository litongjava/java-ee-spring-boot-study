package com.litongjava.webmgic.selenium;

import org.springframework.scheduling.annotation.Scheduled;

import us.codecraft.webmagic.Spider;

public class StartCrawler {

  private JdChromeDownloader downloader;

  private JpaPipeline jpaPipeline;

  // 声明搜索页的初始地址
  String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8"
      + "&suggest=1.his.0.0&wq=&pvid=72c93b8e6951419f83e22a7daee906d0";

  @Scheduled(cron = "0/5 * * * * *")
  public void run() {
    downloader = new JdChromeDownloader();
    jpaPipeline = new JpaPipeline();
    Spider.create(new JdPageProcessor())
        // .addUrl("https://www.jd.com/news.html?id=38673")
        .addUrl(url)
        // 设置下载器
        .setDownloader(downloader)
        // 设置使用jpa的输出
        .addPipeline(jpaPipeline).run();
  }
}
