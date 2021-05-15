package kz.bigdata.web.model.web;

import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class Client {

  public String ctn;
  public String iin;
  public int smartphoneId;
  public int income;
  public LocalDateTime createdAt;

}
