package com.alit.seralizable.servlet;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.alit.seralizable.file.User;

public class Client {
  public static void main(String[] args) {
    String address = "http://localhost:8080/ServerServlet";
    URL url = null;
    try {
      url = new URL(address);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    User user = new User();
    user.setAge(18);
    user.setName("litong");
    HttpURLConnection connection = null;
    ObjectOutputStream objectOutput = null;
    ObjectInputStream objectInput = null;
    try {
      connection = (HttpURLConnection) url.openConnection();
      connection.setDoInput(true);
      connection.setDoOutput(true);
      OutputStream out = connection.getOutputStream();
      objectOutput = new ObjectOutputStream(out);
      System.out.println("发送数据:" + user);
      objectOutput.writeObject(user);

      InputStream in = connection.getInputStream();
      objectInput = new ObjectInputStream(in);
      try {
        user = (User) objectInput.readObject();
        System.out.println("接收到数据" + user);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      close(objectOutput);
      close(objectInput);
    }
  }

  private static void close(Closeable io) {
    if (io != null) {
      try {
        io.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
