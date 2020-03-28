package com.startup.apnaapartment.dao;

import com.google.common.collect.ImmutableMap;
import com.startup.apnaapartment.helper.MysqlDaoHelper;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends MysqlDaoHelper implements UserDao {

  private final Map<String, String> DAO_ATTRS = new ImmutableMap.Builder<String, String>()
      .put("tableName", TABLE_NAME)
      .put("userid", USER_ID)
      .put("password", PASSWORD)
      .build();
  private String INSERT_NEW_USER = StrSubstitutor
      .replace("INSERT INTO ${tableName} (${userid}, ${password}) VALUES(?, ?)", DAO_ATTRS);


  @Autowired
  public UserDaoImpl(DataSource dataSource) {
    super(dataSource, 10);
  }

  @Override
  @SneakyThrows
  public CompletionStage<Integer> addNewUser(String userId, String password) {
    return update(INSERT_NEW_USER, userId, password);
  }
}
