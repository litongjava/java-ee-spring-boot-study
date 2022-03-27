package com.litongjava.spring.boot.webmagic;

import com.litongjava.spring.boot.model.SmzdmModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

@Slf4j
public class SmzdmPageProcessor implements PageProcessor {

  // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
  // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
  private Site site = Site.me().setUserAgent(UserAgent.Mozilla_5_0)
    .setTimeOut(10 * 1000).setRetryTimes(3).setRetrySleepTime(3000);

  @Override
  public void process(Page page) {
    // 获取url地址,测试成功
    Selectable selectable = page.getUrl();
    log.info("selectable:{}", selectable);

    // 部分二：定义如何抽取页面信息，并保存下来
    Html html = page.getHtml();
    if (page.getUrl().regex("https://search.smzdm.com/\\?c=faxian&s=GU&v=b&p=\\d+").match()) {
      //获取页码信息
      String pageXpath = "//ul[@id='J_feed_pagenation']/li/a";
      List<String> pageUrl = html.xpath(pageXpath).links().all();
      //获取商品详情信息
      String storeDetailXpath = "//div[@class=feed-main-con]/ul[@id='feed-main-list']/li/div/div[@class='z-feed-content']/h5/a";
      List<String> storeUrl = html.xpath(storeDetailXpath).links().all();
      page.addTargetRequests(pageUrl);
      page.addTargetRequests(storeUrl);
    } else {
      SmzdmModel smzdmModel = new SmzdmModel();
      String imgLocation = html
        .xpath(
          "//section[@id='feed-wrap']/article/div[@id='feed-main']/div[@class='info']/a/img[@class=main-img]/@src")
        .get();
      // 获取物品的url
      String url = html.xpath("//section[@id='feed-wrap']/article/div[@id='feed-main']/div[@class='info']/a/@href")
        .get();
      String title = html.xpath(
        "//section[@id='feed-wrap']/article/div[@id='feed-main']/div[@class='info']/div[@class='info-right']/div[@class='title-box']/h1[@class='title']/text()")
        .get();
      String price = html.xpath(
        "//section[@id='feed-wrap']/article/div[@id='feed-main']/div[@class='info']/div[@class='info-right']/div[@class='title-box']/div[@class='price']/span/text()")
        .get();
      String introduce = html.xpath(
        "//section[@id='feed-wrap']/article/div[@id='feed-main']/div[@class='item-name']/article/div[@class='baoliao-block']/p/text()")
        .get();
      String baoliao = html
        .xpath("//section[@id='feed-wrap']/article/div[@id='feed-main']/div[@class='item-name']/article/p/text()")
        .get();
      String time = html.xpath(
        "//section[@id='feed-wrap']/article/div[@id='feed-main']/div[@class='info']/div[@class='info-right']/div[@class='info-details']/div[@class='author-info']/span[@class='time']/text()")
        .get();
      String zhi = html.xpath(
        "//section[@id='feed-wrap']/article/div[@id='feed-main']/div[@class='item-name']/div[@class='score_rateBox']/div[@class='score_rate']/span[@id='rating_worthy_num']/text()")
        .get();
      String buZhi = html.xpath(
        "//section[@id='feed-wrap']/article/div[@id='feed-main']/div[@class='item-name']/div[@class='score_rateBox']/div[@class='score_rate']/span[@id='rating_unworthy_num']/text()")
        .get();
      String start = html.xpath(
        "//section[@id='feed-wrap']/article/div[@id='feed-main']/div[@class='item-name']/div[@class='operate_box']/div[@class='operate_icon']/a[@class='fav']/span/text()")
        .get();
      String pl = html.xpath(
        "//section[@id='feed-wrap']/article/div[@id='feed-main']/div[@class='item-name']/div[@class='operate_box']/div[@class='operate_icon']/a[@class='comment']/em[@class='commentNum']/text()")
        .get();
      if (StringUtils.isBlank(introduce)) {
        smzdmModel.setIntroduce(baoliao);
      }
      // time = TimeUtil.handSmzdm(time);
      smzdmModel.setUrl(url);
      smzdmModel.setTitle(title);
      smzdmModel.setPrice(price);
      smzdmModel.setIntroduce(introduce);
      smzdmModel.setFbtime(time);
      smzdmModel.setNoZhi(buZhi);
      smzdmModel.setZhi(zhi);
      smzdmModel.setStart(start);
      smzdmModel.setPl(pl);
      smzdmModel.setImgurl(imgLocation);
      // 将爬取结果存储起来，key为smzdm value为爬取的数据即为smzdmModel的对象
      page.putField("smzdm", smzdmModel);
    }
  }

  @Override
  public Site getSite() {
    return site;
  }
}
