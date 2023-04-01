package com.litongjava.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

public class ProxyTest implements PageProcessor {
  @Override
  public void process(Page page) {
    System.out.println("获取到的自己的ip地址是:");
    System.out.println(page.getHtml().css("center", "text").get());
  }

  private Site site = Site.me();

  @Override
  public Site getSite() {
    return site;
  }

  public static void main(String[] args) {
    String proxyIp = "222.74.73.202";
    int proxyPort = 42055;
    Proxy proxy = new Proxy(proxyIp, proxyPort);
    SimpleProxyProvider simpleProxyProvider = SimpleProxyProvider.from(proxy);

    // 创建下载器
    HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
    // 设置代理服务器
    httpClientDownloader.setProxyProvider(simpleProxyProvider);

    Spider.create(new ProxyTest()).addUrl("http://2019.ip138.com/ic.asp")
        // 把设置好代理服务器的下载器进行使用
        .setDownloader(httpClientDownloader).run();
  }
}
