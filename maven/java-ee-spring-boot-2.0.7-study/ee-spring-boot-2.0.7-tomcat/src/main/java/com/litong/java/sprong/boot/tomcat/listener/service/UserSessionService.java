package com.litong.java.sprong.boot.tomcat.listener.service;

/**
 * @author litongjava
 * @date 2020年9月14日_上午11:56:08 
 * @version 1.0 
 * @desc
 */

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class UserSessionService {
  /**
   * 1 船舶交易
   * 2 经济监测
   * 3 新闻
   */
  private volatile Map<Integer, List<String>> userMap = new ConcurrentHashMap<Integer, List<String>>();

}
