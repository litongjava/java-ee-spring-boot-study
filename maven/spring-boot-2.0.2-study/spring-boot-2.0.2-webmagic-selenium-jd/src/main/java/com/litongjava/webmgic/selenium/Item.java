package com.litongjava.webmgic.selenium;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
  private long spu, sku;
  private Date created, Updated;
  private String title, price, url;
}
