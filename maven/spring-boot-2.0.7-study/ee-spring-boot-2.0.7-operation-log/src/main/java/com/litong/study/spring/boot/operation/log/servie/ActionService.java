package com.litong.study.spring.boot.operation.log.servie;

import org.springframework.stereotype.Service;

import com.litong.study.spring.boot.operation.log.bean.Action;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActionService {
  public void save(Action action) {
    log.info(action.toString());
  }
}
