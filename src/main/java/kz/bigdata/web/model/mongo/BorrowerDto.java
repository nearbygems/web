package kz.bigdata.web.model.mongo;

import kz.bigdata.web.model.web.Borrower;
import kz.bigdata.web.util.DateUtil;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@ToString
@FieldNameConstants
public class BorrowerDto {

  public ObjectId id;
  public String ctn;
  public LocalDateTime eventTime;

  public static BorrowerDto valueFromCsvRow(String row) {

    var values = row.trim().split(";");

    if (values.length != 3) {
      return null;
    }

    var ret = new BorrowerDto();
    ret.ctn = values[1].trim();
    ret.eventTime = LocalDateTime.parse(values[2].trim(), DateUtil.dateFormat());

    return ret;
  }

  public Borrower web() {
    var ret = new Borrower();
    ret.ctn = ctn;
    ret.eventTime = eventTime;
    return ret;
  }

}
