package kz.bigdata.web.model.web;

import lombok.ToString;

@ToString
public class Result {

  public String result;

  public static Result approved() {
    var ret = new Result();
    ret.result = ResultType.APPROVED.text();
    return ret;
  }

}
