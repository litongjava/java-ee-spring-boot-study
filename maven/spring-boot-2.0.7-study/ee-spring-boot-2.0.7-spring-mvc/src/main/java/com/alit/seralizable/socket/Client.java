package com.alit.seralizable.socket;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

  public static void main(String[] args) {
    try {
      Socket socket = new Socket("127.0.0.1", 8080);
      ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
      ClientUser user = new ClientUser();
      user.setAge(18);
      user.setName("jack");
      System.out.println("发送的服务端数据:" + user);
      objectOutput.writeObject(user);
      ObjectInput objectInput = new ObjectInputStream(socket.getInputStream());
      user = (ClientUser) objectInput.readObject();
      System.out.println("接受服务端返回:" + user);
      objectInput.close();
      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}