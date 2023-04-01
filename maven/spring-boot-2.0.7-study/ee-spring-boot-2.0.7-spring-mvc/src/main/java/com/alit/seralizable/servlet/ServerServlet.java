package com.alit.seralizable.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alit.seralizable.file.User;

@WebServlet("/ServerServlet")
public class ServerServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ObjectInputStream objectInput = new ObjectInputStream(request.getInputStream());
    User user = null;
    try {
      user = (User) objectInput.readObject();
      System.out.println("接收到对象:" + user);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (objectInput != null) {
        objectInput.close();
      }
    }

    user = new User();
    user.setAge(20);
    user.setName("Tom");
    ObjectOutput objectOutput = new ObjectOutputStream(response.getOutputStream());
    System.out.println("发送对象:" + user);
    objectOutput.writeObject(user);
    objectOutput.flush();
    objectOutput.close();
  }
}