package com.litongjava.webmgic.selenium;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class JdPageProcessor implements PageProcessor {
  @Override
  public void process(Page page) {
    // System.out.println(page.getHtml().css("div.mt h1", "text"));
    // 获取页面中的商品列表数据,只有搜索结果页才有商品列表
    List<Selectable> nodes = page.getHtml().css("#J_goodsList li.gl-item").nodes();

    // 判断nodes是否有值
    if (nodes != null && nodes.size() > 0) {
      // 如果有值表示是搜索结果页

      // 声明存放商品的集合
      List<Item> itemList = new ArrayList<>();

      // 遍历商品项
      for (Selectable node : nodes) {
        // 获取商品spu
        String spu = node.css("li", "data-spu").get();

        // 获取商品的sku,一个spu有可能有多个sku
        List<String> skuList = node.css("li.ps-item img", "data-sku").all();

        // 遍历sku
        for (String sku : skuList) {
          // 创建对象
          Item item = new Item();

          // 设置数据
          item.setSpu(Long.parseLong(spu));
          item.setSku(Long.parseLong(sku));
          item.setCreated(new Date());
          item.setUpdated(item.getCreated());

          // 放到集合中
          itemList.add(item);

          // 把商品详情页的url放到url任务队列中
          page.addTargetRequest("https://item.jd.com/" + sku + ".html");
        }

      }

      // 把需要持久化的数据放到ResultItems中
      page.putField("itemList", itemList);

    } else {
      // 如果没有值表示是商品详情页
      // 创建商品对象
      Item item = new Item();
      String sku = page.getHtml().css("div.left-btns a.J-follow", "data-id").get();

      item.setSku(Long.parseLong(sku));
      item.setTitle(page.getHtml().css("div.sku-name", "text").get());
      item.setPrice(page.getHtml().css("span.p-price span.price", "text").get());
      item.setUrl(page.getUrl().toString());

      // 保存到ResultItems中
      page.putField("item", item);
    }

  }

  private Site site = Site.me().setTimeOut(2000);

  @Override
  public Site getSite() {
    return site;
  }
}
