package com.alit.seralizable.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(8080);
      System.out.println("服务端启动");
      Socket socket = serverSocket.accept();
      ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
      ServerUser user = (ServerUser) objectInput.readObject();
      System.out.println("接收到客户端的数据:" + user);
      ObjectOutput objectOutput = new ObjectOutputStream(socket.getOutputStream());
      user = new ServerUser();
      user.setAge(12);
      user.setName("Tom");
      System.out.println("返回到客户端数据:" + user);
      objectOutput.writeObject(user);
      objectOutput.flush();
      objectOutput.close();
      serverSocket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}