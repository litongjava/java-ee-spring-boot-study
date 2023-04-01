package com.litongjava.spring.boot.mybatis.plus.h2.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.litongjava.spring.boot.mybatis.plus.h2.convert.GoodsTypesHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(autoResultMap = true)
public class GooldsDetail {
  private Long id;
  @TableField(typeHandler = GoodsTypesHandler.class)
  private List<Integer> types;
}
