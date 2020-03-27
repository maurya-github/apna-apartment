package com.startup.apnaapartment.controller;

import com.startup.apnaapartment.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

  StatusService statusService;

  @Autowired
  public StatusController(StatusService statusService) {
    this.statusService = statusService;
  }

  @RequestMapping("/health")
  public String serviceHealth() {
    return statusService.serviceStatus();
  }
}
