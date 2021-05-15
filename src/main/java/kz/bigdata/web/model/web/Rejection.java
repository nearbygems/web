package kz.bigdata.web.model.web;

import lombok.ToString;

@ToString
public class Rejection extends Result {

  public String reason;

  public static Rejection of(ReasonType type) {
    var ret = new Rejection();
    ret.result = ResultType.REJECTED.text();
    ret.reason = type.text();
    return ret;
  }

}
