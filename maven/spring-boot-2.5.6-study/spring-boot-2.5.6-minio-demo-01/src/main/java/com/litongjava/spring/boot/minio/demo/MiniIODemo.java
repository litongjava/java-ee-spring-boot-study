package com.litongjava.spring.boot.minio.demo;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import io.minio.BucketExistsArgs;
import io.minio.DownloadObjectArgs;
import io.minio.MinioClient;
import io.minio.MinioClient.Builder;
import io.minio.ObjectStat;
import io.minio.StatObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MiniIODemo {

  public static void main(String[] args) throws InvalidKeyException, ErrorResponseException, IllegalArgumentException,
      InsufficientDataException, InternalException, InvalidBucketNameException, InvalidResponseException,
      NoSuchAlgorithmException, ServerException, XmlParserException, IOException {

    String endpoint = "http://192.168.3.9";
    int port = 9002;
    String accessKey = "root";
    String secretKey = "robot_123456#";
    Builder builder = MinioClient.builder();

    MinioClient minioClient = builder.endpoint(endpoint, port, false)
        //
        .credentials(accessKey, secretKey).build();

    String bucketName = "fsp-dev";
    boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket("fsp-dev").build());
    String filePath = "F:\\opencv-images\\face\\huge.png";
    File file = new File(filePath);
    String objectName = file.getName();

    if (bucketExists) {
      StatObjectArgs statObjectArgs = StatObjectArgs.builder().bucket(bucketName).object(objectName).build();
      ObjectStat objectStat = minioClient.statObject(statObjectArgs);
      log.info("objectStat:{}", objectStat);
      if (objectStat != null && objectStat.length() > 0) {
        DownloadObjectArgs.Builder downloadObjectBuilder = DownloadObjectArgs.builder();
        downloadObjectBuilder.bucket(bucketName).object(objectName);
        downloadObjectBuilder.filename("download-001.jpg");
        DownloadObjectArgs downloadObjectArgs = downloadObjectBuilder.build();
        minioClient.downloadObject(downloadObjectArgs);
      }

    }
  }
}
