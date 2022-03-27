package com.litongjava.webmagic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

@Component
public class JobPageProcessor implements PageProcessor {

  @Autowired
  private JpaPipeline jpaPipeline;

  //String url = "https://www.jd.com/news.html?id=38673";
  String url = "https://search.51job.com/list/010000,000000,0000,32%252C38,9,99,java,2,1.html?"
      + "lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99"
      + "&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

  private Site site = Site.me().setTimeOut(10 * 1000); // 超时10s  

  @Override
  public Site getSite() {
    return site;
  }
  @Override
  public void process(Page page) {
    // 测试代码
    //page.putField("content",page.getHtml().css("div.mt h1","text").all());

    // 获取列表页的职位详情url
    List<String> urlList = page.getHtml().css("div#resultList div.el p.t1").links().all();
    //urlList.forEach(e -> System.out.println(e));

    // urlList没有值，页面是职位详情页，如果有值，是职位列表页
    if (urlList.size() > 0) {
      // 把职位详情url放到url管理列表中
      page.addTargetRequests(urlList);
      // 获取下一页的地址,到这就一直不会停，会一直下一页，具体原因参考csdn架构图
      page.addTargetRequests(page.getHtml().css("li.bk").links().all());
    } else {
      // 解析页面并存放结果到ResultItems里
      parseJobInfo(page);
    }
  }

  private void parseJobInfo(Page page) {
    // 创建职位详情对象，用来存放解析的数据
    JobInfo jobInfo = new JobInfo();
    // 解析页面获取数据
    Html html = page.getHtml();
    jobInfo.setJobName(html
        .css("body > div.tCompanyPage > div.tCompany_center.clearfix > div.tHeader.tHjob > div > div.cn > h1", "text")
        .get());
    jobInfo.setSalary(
        html.css("body > div.tCompanyPage > div.tCompany_center.clearfix > div.tHeader.tHjob > div > div.cn > strong",
            "text").get());
    jobInfo.setCompanyName(html.css(
        "body > div.tCompanyPage > div.tCompany_center.clearfix > div.tHeader.tHjob > div > div.cn > p.cname > a.catn",
        "text").get());
    jobInfo.setJobAddr(html
        .css("body > div.tCompanyPage > div.tCompany_center.clearfix > div.tHeader.tHjob > div > div.cn > p.msg.ltype",
            "text")
        .get());
    jobInfo.setJobInfo(
        html.css("body > div.tCompanyPage > div.tCompany_center.clearfix > div.tCompany_main > div:nth-child(1) > div",
            "text").get());
    jobInfo.setUrl(page.getUrl().toString());
    // 封装好的职位详情数据存放到resultItems中
    page.putField("jobInfo", jobInfo);
  }

  // 添加定时任务配置
  // initialDelay，项目启动成功后，多久执行任务，单位毫秒
  // fixedDelay，任务执行完成后，间隔多久下一次任务执行，单位毫秒
  @Scheduled(initialDelay = 1000, fixedDelay = 10000)
  public void run() {
    Spider.create(new JobPageProcessor())
        // 使用自定义的PipeLine保存数据
        .addPipeline(jpaPipeline).addUrl(url).thread(20).run();
  }


}
