package com.alit.seralizable.rmi;

import java.util.List;

import com.alit.seralizable.file.User;

public interface UserService {
  public List<User> geAlltUsers();

  public User getUser(String id);

  public void save(User user);

  public void saves(List<User> users);
}