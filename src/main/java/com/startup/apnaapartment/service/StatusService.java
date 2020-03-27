package com.startup.apnaapartment.service;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

  @Autowired
  DataSource dataSource;

  public String serviceStatus() {
    return "DB UP: " + isDBUP() + " Application is running";
  }

  public boolean isDBUP() {
    Connection connection = null;
    boolean dbStatus;
    try {
      connection = dataSource.getConnection();
      dbStatus = connection.prepareStatement("SELECT 1").execute();
    } catch (SQLException e) {
      return false;
    }

    return dbStatus;
  }
}
