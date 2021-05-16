package kz.bigdata.web.dao.model;

import kz.bigdata.web.model.web.Rejection;
import kz.bigdata.web.model.web.Result;
import org.apache.ibatis.annotations.Insert;

import java.time.LocalDateTime;

public interface LogDao {

  @Insert(
    "INSERT INTO logs (ctn, result, reason, log_timestamp) " +
      "VALUES (#{ctn}, #{rejection.result}, #{rejection.reason}, #{now})"
  )
  void rejection(Rejection rejection, String ctn, LocalDateTime now);

  @Insert(
    "INSERT INTO logs (ctn, result, log_timestamp) " +
      "VALUES (#{ctn}, #{result.result}, #{now})"
  )
  void result(Result result, String ctn, LocalDateTime now);

}
