package com.alit.seralizable.rmi;

import java.util.List;

import com.alit.seralizable.file.User;

public class UserServiceImpl implements UserService {
  @Override
  public List<User> geAlltUsers() {return UsersFactory.getUsers();}
  @Override
  public User getUser(String id) {return UsersFactory.getUser(id);}
  @Override
  public void save(User user) {UsersFactory.svae(user);}
  @Override
  public void saves(List<User> users) {for (User u : users) {UsersFactory.svae(u);}}
}