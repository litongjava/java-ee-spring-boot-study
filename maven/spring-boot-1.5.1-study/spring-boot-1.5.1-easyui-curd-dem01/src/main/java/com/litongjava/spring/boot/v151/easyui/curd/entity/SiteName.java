package com.litongjava.spring.boot.v151.easyui.curd.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "dict_site_name")
public class SiteName {
  @Id
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "system-uuid")
  @Column(nullable = false)
  private String id;
  @NotNull(message = "站定名称不能为空")
  private String siteName;
}