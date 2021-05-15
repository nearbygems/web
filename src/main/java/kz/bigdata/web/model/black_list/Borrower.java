package kz.bigdata.web.model.black_list;

import kz.bigdata.web.model.mongo.BorrowerDto;
import kz.bigdata.web.util.DateUtil;
import kz.bigdata.web.util.Ids;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class Borrower {

  public String ctn;
  public LocalDateTime eventTime;

  public static Borrower valueFromCsvRow(String row) {
    var values = row.trim().split(";");
    if (values.length != 3) {
      return null;
    }
    var ret = new Borrower();
    ret.ctn = values[1].trim();
    ret.eventTime = LocalDateTime.parse(values[2].trim(), DateUtil.dateFormat());
    return ret;
  }

  public BorrowerDto toDto() {
    var ret = new BorrowerDto();
    ret.id = Ids.generate();
    ret.ctn = ctn;
    ret.eventTime = eventTime;
    return ret;
  }

}
