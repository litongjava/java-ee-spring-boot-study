package com.litong.study.spring.boot.v158.excel.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.litong.study.spring.boot.v158.excel.ImportMp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ImportMp.class)
public class FeedbackEmpMapperTest {
  /**
   * foreach 多线程
   */

  @Autowired
  FeedbackEmpMapper feedbackEmpMapper;

  @Test
  public void addForeachExecutor() {
    try {
      long start = System.currentTimeMillis();
      List<FeedbackEmp> list = this.getData();
      this.exec(list, 1000);
      long end = System.currentTimeMillis();
      System.out.println("插入耗时:--------------------------" + (start - end) + "--------------------------");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 多线程处理
   *
   * @param list     数据
   * @param dealSize 每个线程处理数量
   * @throws Exception
   */

  private void exec(List<FeedbackEmp> list, int dealSize) throws Exception {
    if (!CollectionUtils.isEmpty(list)) {
      // 数据总的大小
      int count = list.size();
      // 每个线程数据集
      List<FeedbackEmp> threadList = null;
      // 线程池
      int runSize = (count / dealSize) + 1;
      ThreadPoolExecutor executor = new ThreadPoolExecutor(runSize, 350, 30L, TimeUnit.SECONDS, new SynchronousQueue<>());
      // 计数器
      CountDownLatch countDownLatch = new CountDownLatch(runSize);
      for (int i = 0; i < runSize; i++) {
        // 计算每个线程执行的数据
        int startIndex = (i * dealSize);
        if ((i + 1) == runSize) {
          int endIndex = count;
          threadList = list.subList(startIndex, endIndex);
        } else {
          int endIndex = (i + 1) * dealSize;
          threadList = list.subList(startIndex, endIndex);
        }

        // 线程任务
        MyThread myThread = new MyThread(feedbackEmpMapper, threadList, countDownLatch);
        executor.execute(myThread);
      }
      // 计数
      countDownLatch.await();
      // 关闭线程池
      executor.shutdown();
    }
  }

  private List<FeedbackEmp> getData() {
    List<FeedbackEmp> list = new ArrayList<>();
    FeedbackEmp entity = null;
    for (int i = 0; i < 300000; i++) {
      entity = new FeedbackEmp();
      entity.setId(i + "");
      entity.setFeedbackId("0");
      entity.setEmpId("0");
      list.add(entity);
    }
    return list;
  }
}
