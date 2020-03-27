package com.startup.apnaapartment.dao;

import java.util.concurrent.CompletionStage;

public interface UserDao {

  String TABLE_NAME = "users";
  String USER_ID = "userid";
  String PASSWORD = "password";

  public CompletionStage<Integer> addNewUser(String userId, String password);
}
