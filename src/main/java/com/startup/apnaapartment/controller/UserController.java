package com.startup.apnaapartment.controller;

import com.startup.apnaapartment.model.request.AddNewUserRequest;
import com.startup.apnaapartment.service.UserService;
import java.util.concurrent.CompletionStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/users")
  public CompletionStage<Integer> addNewUser(@RequestBody AddNewUserRequest addNewUserRequest) {
    return userService.addNewUser(addNewUserRequest);
  }
}
