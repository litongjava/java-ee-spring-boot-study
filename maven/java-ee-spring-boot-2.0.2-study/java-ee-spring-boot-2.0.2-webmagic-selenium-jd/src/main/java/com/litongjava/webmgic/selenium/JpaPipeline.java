package com.litongjava.webmgic.selenium;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Slf4j
public class JpaPipeline implements Pipeline {

  @Override
  public void process(ResultItems resultItems, Task task) {
    // 获取商品列表页数据
    List<Item> itemList = resultItems.get("itemList");

    if (itemList != null && itemList.size() > 0) {
      log.info("itemList:{}", itemList);
    }

    // 获取商品详情页数据
    Item item = resultItems.get("item");
    if (item != null) {
      log.info("item:{}", item);
    }

  }
}
