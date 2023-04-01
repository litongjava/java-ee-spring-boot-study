package com.litongjava.spring.boot.v151.easyui.curd.services;

import com.litongjava.spring.boot.v151.easyui.curd.entity.SiteName;
import com.litongjava.spring.boot.v151.easyui.curd.repository.SiteNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SiteNameService {

  @Autowired
  private SiteNameRepository snr;

  public Page<SiteName> paginage(int page, int rows) {
    PageRequest pageRequest = new PageRequest(page - 1, rows);
    Page<SiteName> findAll = snr.findAll(pageRequest);
    return findAll;
  }
}