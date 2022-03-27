package com.uairobot.bill.study.spring.boot.upload;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFileEntity, String> {
  Optional<UploadFileEntity> findByMd5(String md5Hex);
}