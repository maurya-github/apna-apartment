package com.startup.apnaapartment.service;

import com.startup.apnaapartment.dao.UserDao;
import com.startup.apnaapartment.model.request.AddNewUserRequest;
import java.util.concurrent.CompletionStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  UserDao userDao;

  @Autowired
  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public CompletionStage<Integer> addNewUser(AddNewUserRequest addNewUserRequest) {
    return userDao.addNewUser(addNewUserRequest.getUserid(), addNewUserRequest.getPassword());
  }
}
