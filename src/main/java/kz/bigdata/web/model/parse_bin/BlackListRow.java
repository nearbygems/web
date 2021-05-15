package kz.bigdata.web.model.parse_bin;

import kz.bigdata.web.util.DateUtil;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@ToString
@FieldNameConstants
public class BlackListRow {

  public int order;
  public String phone;
  public LocalDateTime eventTime;

  public static String header() {
    return Fields.order + ";" + Fields.phone + ";" + Fields.eventTime;
  }

  public String toCsvRow() {
    return order + ";" + phone + ";" + eventTime.format(DateUtil.dateFormat());
  }

}
