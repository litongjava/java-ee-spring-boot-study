package com.litongjava.spring.boot.v151.easyui.curd.repository;

import java.util.List;

import com.litongjava.spring.boot.v151.easyui.curd.entity.SiteName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface SiteNameRepository extends JpaRepository<SiteName,String>{

  @Transactional
  @Modifying
  @Query("update SiteName u set u.siteName = ?2 where u.id = ?1")
  Integer updateSiteNameById(String id, String siteName);
}