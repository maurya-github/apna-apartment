package com.startup.apnaapartment.runner;

import com.startup.apnaapartment.configuration.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApnaApartmentApplication {

  public static void main(String[] args) {
    SpringApplication.run(AppConfiguration.class, args);
  }

}
