package kz.bigdata.web.model.web;

import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class Borrower {

  public String        ctn;
  public LocalDateTime eventTime;

}
