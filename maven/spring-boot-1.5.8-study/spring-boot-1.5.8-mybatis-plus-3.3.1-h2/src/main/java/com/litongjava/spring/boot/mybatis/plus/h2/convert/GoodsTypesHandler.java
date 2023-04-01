package com.litongjava.spring.boot.mybatis.plus.h2.convert;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;

@MappedTypes({List.class, Integer.class})
public class GoodsTypesHandler extends AbstractJsonTypeHandler<List<Integer>> {
  @Override
  protected String toJson(List<Integer> obj) {
    return JSONUtil.toJsonStr(obj);
  }

  @Override
  protected List<Integer> parse(String json) {
    return JSONUtil.parseArray(json).toList(Integer.class);
  }
}

