package com.incesoft.ai.commonsapi.service.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.junit.Test;
import org.springframework.remoting.support.RemoteInvocation;

public class AskRequestTest {

  /**
   * 测试返反序列化
   * 
   * @throws IOException
   * @throws ClassNotFoundException
   */
  @Test
  public void test() throws IOException, ClassNotFoundException {
    FileInputStream in = new FileInputStream("ai-request.txt");
    ObjectInputStream objectInput = new ObjectInputStream(in);
    RemoteInvocation remoteInvocation = (RemoteInvocation) objectInput.readObject();
    System.out.println(remoteInvocation);

  }

}
