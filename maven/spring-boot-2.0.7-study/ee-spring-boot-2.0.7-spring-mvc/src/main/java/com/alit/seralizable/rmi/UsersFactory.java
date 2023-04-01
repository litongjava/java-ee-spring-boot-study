package com.alit.seralizable.rmi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alit.seralizable.file.User;

public class UsersFactory {
  private static List<User> users = null;
  static {
    users = new ArrayList<>();
    User user1 = new User();
    user1.setId(UUID.randomUUID().toString().replace("-", ""));
    user1.setAge(17);
    user1.setName("赵大");
    users.add(user1);
    User user2 = new User();
    user2.setId(UUID.randomUUID().toString().replace("-", ""));
    user2.setAge(18);
    user2.setName("钱二");
    users.add(user2);
  }

  public static List<User> getUsers() {
    return users;
  }

  public static User getUser(String id) {
    for (User user : users) {
      if (user.getId().endsWith(id)) {
        return user;
      }
    }
    return null;
  }

  public static void svae(User user) {
    users.add(user);
  }
}
