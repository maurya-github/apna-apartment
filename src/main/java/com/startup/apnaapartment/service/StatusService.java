package com.startup.apnaapartment.service;

import org.springframework.stereotype.Service;

@Service
public class StatusService {


  public String serviceStatus() {
    return "Application is UP";
  }
}
