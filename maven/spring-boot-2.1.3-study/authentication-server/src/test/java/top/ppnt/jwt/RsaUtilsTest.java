package top.ppnt.jwt;

import org.junit.Test;

import top.ppnt.spring.boot.utils.rsa.RsaUtils;

public class RsaUtilsTest {

  private String privateFilePath = "id_key_rsa";
  private String publicFilePath = "id_key_rsa.pub";

  @Test
  public void generateKey() throws Exception {
    RsaUtils.generateKey(publicFilePath, privateFilePath, "robot_123456#", 2048);
  }

  @Test
  public void getPublicKey() throws Exception {
    System.out.println(RsaUtils.getPublicKey(publicFilePath));
  }

  @Test
  public void getPrivateKey() throws Exception {
    System.out.println(RsaUtils.getPrivateKey(privateFilePath));
  }

}