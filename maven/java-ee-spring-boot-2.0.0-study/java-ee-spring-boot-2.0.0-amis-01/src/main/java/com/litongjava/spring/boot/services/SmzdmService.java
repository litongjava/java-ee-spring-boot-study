package com.litongjava.spring.boot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litongjava.spring.boot.mapper.SmzdmMapper;
import com.litongjava.spring.boot.model.SmzdmModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmzdmService extends ServiceImpl<SmzdmMapper, SmzdmModel> {
  public List<SmzdmModel> selectAll() {
    log.info("select all");
    return baseMapper.selectAll();
  }
}