package com.litongjava.webmgic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

@Component
public class JdChromeDownloader implements Downloader {

  // 声明驱动
  private RemoteWebDriver driver;

  public JdChromeDownloader() {
    // 第一个参数是使用哪种浏览器驱动
    // 第二个参数是浏览器驱动的地址
    String chromeDriverPath = "C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver\\chromedriver.exe";
    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    // 创建浏览器参数对象
    ChromeOptions chromeOptions = new ChromeOptions();

    // 设置为 headless 模式,上课演示,或者学习不要打开
    // chromeOptions.addArguments("--headless");
    // 设置浏览器窗口打开大小
    chromeOptions.addArguments("--window-size=1280,700");

    // 创建驱动
    this.driver = new ChromeDriver(chromeOptions);
  }

  @Override
  public Page download(Request request, Task task) {
    try {
      driver.get(request.getUrl());
      Thread.sleep(2000);

      // 无论是搜索页还是详情页,都滚动到页面底部,所有该加载的资源都加载
      // 需要滚动到页面的底部,获取完整的商品数据
      driver.executeScript("window.scrollTo(0, document.body.scrollHeight - 1000)");
      Thread.sleep(2000l);

      // 获取页面对象
      Page page = createPage(request.getUrl(), driver.getPageSource());

      // 判断是否是搜索页
      if (request.getUrl().contains("search")) {
        // 如果请求url包含search,说明是搜索结果页
        // 在搜索结果页,需要获取下一页的链接地址
        // 点击下一页按钮,在下一页中获取当前页的url(就是下一页的url),放到任务队列中
        WebElement next = driver.findElement(By.cssSelector("a.pn-next"));
        // 点击
        next.click();

        // 获取当前页面(其实就是下一页)的url地址
        String nextUrl = driver.getCurrentUrl();

        // 使用page对象,把下一页url放到任务列表中
        page.addTargetRequest(nextUrl);
      }

      // 关闭浏览器
      // driver.close();

      return page;

    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public void setThread(int threadNum) {

  }

  // 构建page返回对象
  private Page createPage(String url, String content) {
    Page page = new Page();
    page.setRawText(content);
    page.setUrl(new PlainText(url));
    page.setRequest(new Request(url));
    page.setDownloadSuccess(true);

    return page;
  }

}
