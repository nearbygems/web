package kz.bigdata.web.model.black_list;

import kz.bigdata.web.util.DateUtil;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class Borrower {

  public String ctn;
  public LocalDateTime eventTime;

  public static Borrower valueFromCsvRow(String row) {
    var values = row.trim().split(",");
    if (values.length != 3) {
      return null;
    }
    var ret = new Borrower();
    ret.ctn = values[1].trim();
    ret.eventTime = LocalDateTime.parse(values[2].trim(), DateUtil.dateFormat());
    return ret;
  }

}