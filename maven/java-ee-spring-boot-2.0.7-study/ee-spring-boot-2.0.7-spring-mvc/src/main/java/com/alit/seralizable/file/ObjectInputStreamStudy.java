package com.alit.seralizable.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectInputStreamStudy {
  public static void main(String[] args) {
    User user = new User();
    user.setAge(18);
    user.setName("litong");
    ObjectOutputStream objectOutput = null;
    try {
      // 将对象序列化并写入文件
      FileOutputStream fileOutput = new FileOutputStream("User.txt");
      objectOutput = new ObjectOutputStream(fileOutput);
      objectOutput.writeObject(user);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (objectOutput != null) {
        try {
          // 关闭 objectOutput和fileOutput
          objectOutput.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
