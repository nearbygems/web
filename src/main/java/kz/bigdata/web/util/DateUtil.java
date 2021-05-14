package kz.bigdata.web.util;

import java.time.format.DateTimeFormatter;

public class DateUtil {

  public static DateTimeFormatter dateFormat() {
    return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  }
}
