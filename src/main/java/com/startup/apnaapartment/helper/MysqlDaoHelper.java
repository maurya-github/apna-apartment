package com.startup.apnaapartment.helper;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

public class MysqlDaoHelper {

  DataSource dataSource;
  JdbcTemplate jdbcTemplate;
  TransactionTemplate transactionTemplate;
  NamedParameterJdbcTemplate namedJdbcTemplate;
  ExecutorService executorService;

  public MysqlDaoHelper(DataSource dataSource, int poolSize) {
    this.dataSource = dataSource;
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    transactionTemplate = new TransactionTemplate(
        new DataSourceTransactionManager(Objects.requireNonNull(jdbcTemplate.getDataSource())));
    namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    executorService = Executors.newFixedThreadPool(poolSize, new ThreadFactory() {
      private final AtomicInteger next = new AtomicInteger();

      @Override
      public Thread newThread(Runnable r) {
        return new Thread(r,
            "mysql-" + dataSource + "-thread-" + next.incrementAndGet());
      }
    });
  }

  public CompletionStage<Integer> update(String sql, Object... args) {
    return supplyAsync(() -> this.jdbcTemplate.update(sql, args), executorService);
  }

}
