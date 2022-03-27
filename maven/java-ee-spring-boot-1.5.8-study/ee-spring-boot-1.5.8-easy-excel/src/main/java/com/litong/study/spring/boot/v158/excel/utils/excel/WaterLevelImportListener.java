package com.litong.study.spring.boot.v158.excel.utils.excel;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.litong.study.spring.boot.v158.excel.entity.WaterLevel;
import com.litong.study.spring.boot.v158.excel.entity.WaterLevelRowModel;
import com.litong.study.spring.boot.v158.excel.service.WaterLevelService;
import com.litong.study.spring.boot.v158.excel.utils.StrKit;

import lombok.extern.slf4j.Slf4j;

/***
 *  监听器
 */
@Slf4j
public class WaterLevelImportListener extends AnalysisEventListener<List<String>> {

  private WaterLevelService wls = null;
  private List<String> header = null;
  List<WaterLevelRowModel> datas = new ArrayList<WaterLevelRowModel>();
  List<WaterLevel> entityList = new ArrayList<WaterLevel>();
  List<WaterLevel> entityListBatch = new ArrayList<WaterLevel>();

  public WaterLevelImportListener(WaterLevelService wls) {
    this.wls = wls;
  }

  @Override
  public void invoke(List<String> data, AnalysisContext context) {
    Integer currentRowNum = context.getCurrentRowNum();
    log.info("处理第{}行数据", currentRowNum);
    if (currentRowNum == 0) {
      header = data;
      log.info("导入的表格头部信息:{}", header);
      return;
    }
    getList(data);
    saveOrUpdate(entityList);
    entityList.clear();
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext context) {
    log.info("最后一次提交的数据总量:" + entityListBatch.size());
    if(entityListBatch.size()>0) {
      wls.saveBatch(entityListBatch, entityListBatch.size());
    }
    log.info("保存成功");
    entityListBatch.clear();
  }

  /**
   * 更新或保存数据
   * @param data
   */
  private void saveOrUpdate(List<WaterLevel> entryList) {
    for (WaterLevel wl : entryList) {
      if(wl.getSiteName().equals("宜宾") && wl.getTime().equals("2019-06-15")) {
        System.out.println("进入断点");
      }
      WaterLevel queryEntity = new WaterLevel();
      queryEntity.setSiteName(wl.getSiteName()).setTime(wl.getTime());
      QueryWrapper<WaterLevel> queryWrapper = new QueryWrapper<WaterLevel>(queryEntity);
      List<WaterLevel> list = wls.list(queryWrapper);
      // 如果有多条数据,取出第一条,删除其他条
      WaterLevel one = null;
      if (list.size() > 0) {
        one = list.get(0);
      }

      if (list.size() > 1) {
        List<String> idList = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
          String id = list.get(i).getId();
          idList.add(id);
        }
        wls.removeByIds(idList);
      }
      if (one == null) {
        // 保存数据
        // wls.save(wl);
        // 将数据保存到新的list中,统一提交
        entityListBatch.add(wl);
      } else {
        // 更新数据,判断数据是否相同,如果相同则不更新
        if(wl.getLevel().equals(one.getLevel())) {
          //数据相同,跳过数据
          continue;
        }else {
          //更新数据
          WaterLevel where = new WaterLevel();
          where.setId(one.getId());
          UpdateWrapper<WaterLevel> updateWrapper = new UpdateWrapper<WaterLevel>(where);
          WaterLevel newEntiry = new WaterLevel();
          newEntiry.setLevel(wl.getLevel());
          wls.update(newEntiry, updateWrapper);
        }
      }
    }

  }

  private List<WaterLevel> getList(List<String> data) {
    String date = getDate(data);
    if (date == null) {
      // 读取到了1行空数据,返回null
      return null;
    }
    for (int i = 3; i < data.size(); i++) {
      String level = data.get(i);
      if (StrKit.isEmpty(level)) {
        // 获取到的实际水位值为null,跳过,不添加
        continue;
      }
      String title = header.get(i);
      title = title.replace(" ", ""); // 去除所有空格，包括首尾、中间
      if ("泸州".equals(title) || "二郎滩".equals(title)) {
        title = "泸州：二郎滩";
      } else if ("涪陵".equals(title) || "清溪场".equals(title)) {
        title = "涪陵：清溪场";
      } else if ("涪陵".equals(title) || "长沙".equals(title)) {
        title = "湘江：长沙";
      } else if (title.contains("万县")) {
        title = "万县";
      }
      WaterLevel waterLevel = new WaterLevel();
      waterLevel.setTime(date).setSiteName(title).setLevel(level);
      log.info("构建数据:" + waterLevel);
      entityList.add(waterLevel);
    }
    return entityList;
  }

  String month = "1";
  String year = "2019";

  private String getDate(List<String> list) {
    String day = "1";
    String column0 = list.get(0);
    if (!StrKit.isEmpty(column0)) {
      column0 = StrKit.replaceBlank(column0);
      column0 = StrKit.replaceChinses(column0);
      year = column0;
    }
    String column1 = list.get(1);
    if (!StrKit.isEmpty(column1)) {
      month = getCleanData(column1);
    }
    // System.out.println(month);
    String column2 = list.get(2);
    if (!StrKit.isEmpty(column2)) {
      day = getCleanData(column2);
    } else {
      // 日字段没有值,说明数据错误
      return null;
    }
    // 日期
    String date = year + "-" + month + "-" + day;
    // System.out.println(date);
    return date;
  }

  private String getCleanData(String source) {
    source = StrKit.replaceBlank(source);
    source = StrKit.replaceChinses(source);
    int parseInt = Integer.parseInt(source);
    if (parseInt / 10 < 1) {
      source = "0" + source;
    }
    return source;
  }
}