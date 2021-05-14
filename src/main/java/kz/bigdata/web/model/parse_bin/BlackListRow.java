package kz.bigdata.web.model.parse_bin;

import kz.bigdata.web.util.DateUtil;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class BlackListRow {
  public int order;
  public String phone;
  public LocalDateTime time;

  public String toCsvRow() {
    return order + ", " + phone + ", " + time.format(DateUtil.dateFormat());
  }

}
