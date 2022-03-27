package com.uairobot.bill.study.spring.boot.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;

import com.litong.utils.ip.IPUtil;

@Service
public class IPUtilService {
  @Autowired
  private ServerProperties sp;

  public String getThisUrl() {
    Integer port = sp.getPort();
    String contextPath = sp.getContextPath();
    String thisUrl = IPUtil.getThisUrl(port, contextPath);
    return thisUrl;
  }
}
