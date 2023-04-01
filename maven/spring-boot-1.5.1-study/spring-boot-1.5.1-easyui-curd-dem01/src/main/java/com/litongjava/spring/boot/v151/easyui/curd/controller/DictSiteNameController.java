package com.litongjava.spring.boot.v151.easyui.curd.controller;

import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import com.litongjava.spring.boot.v151.easyui.curd.entity.SiteName;
import com.litongjava.spring.boot.v151.easyui.curd.repository.SiteNameRepository;
import com.litongjava.spring.boot.v151.easyui.curd.services.SiteNameService;
import com.litongjava.spring.boot.v151.easyui.curd.utils.EasyUIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dict/siteName")
public class DictSiteNameController {
  @Autowired
  private SiteNameRepository snr;
  @Autowired
  private SiteNameService sns;

  @RequestMapping("save")
  public Map<String, Object> save(@Valid SiteName siteName) {
    SiteName save = snr.save(siteName);
    if (save != null) {
      return EasyUIUtil.buildSuccess();
    } else {
      return EasyUIUtil.buildFail("添加用户失败");
    }
  }

  @RequestMapping("list")
  public Map<String, Object> list(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
      @RequestParam(name = "rows", defaultValue = "10", required = false) int rows, Date startTime, Date endTime) {
    Page<SiteName> paginage = sns.paginage(page, rows);
    return EasyUIUtil.buildList(paginage.getTotalElements(), paginage.getContent());
  }

  @RequestMapping("update")
  public Map<String, Object> update(@Valid SiteName siteName) {
    snr.updateSiteNameById(siteName.getId(), siteName.getSiteName());
    return EasyUIUtil.buildSuccess();
  }

  @RequestMapping("delete")
  public Map<String, Object> delete(String id) {
    snr.delete(id);
    return EasyUIUtil.buildSuccess();
  }
}