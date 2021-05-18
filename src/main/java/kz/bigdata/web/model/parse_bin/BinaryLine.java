package kz.bigdata.web.model.parse_bin;

import lombok.SneakyThrows;
import lombok.ToString;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@ToString
public class BinaryLine {

  public int    order;
  public String phone;
  public int    year;
  public int    month;
  public int    day;
  public int    hour;
  public int    min;
  public int    sec;

  @SneakyThrows
  public static BinaryLine ofStream(FileInputStream input) {

    var reader = new BinaryLine();

    var order = new byte[4];
    var phone = new byte[10];
    var year  = new byte[2];
    var month = new byte[1];
    var day   = new byte[1];
    var hour  = new byte[1];
    var min   = new byte[1];
    var sec   = new byte[1];

    input.read(order, 0, order.length);
    input.read(phone, 0, phone.length);
    input.read(year, 0, year.length);
    input.read(month, 0, month.length);
    input.read(day, 0, day.length);
    input.read(hour, 0, hour.length);
    input.read(min, 0, min.length);
    input.read(sec, 0, sec.length);

    reader.order = ByteBuffer.wrap(order).order(ByteOrder.BIG_ENDIAN).getInt();
    reader.phone = new String(ByteBuffer.wrap(phone).array(), StandardCharsets.UTF_8);
    reader.year  = ByteBuffer.wrap(year).order(ByteOrder.LITTLE_ENDIAN).getShort();
    reader.month = ByteBuffer.wrap(month).order(ByteOrder.LITTLE_ENDIAN).get();
    reader.day   = ByteBuffer.wrap(day).order(ByteOrder.LITTLE_ENDIAN).get();
    reader.hour  = ByteBuffer.wrap(hour).order(ByteOrder.LITTLE_ENDIAN).get();
    reader.min   = ByteBuffer.wrap(min).order(ByteOrder.LITTLE_ENDIAN).get();
    reader.sec   = ByteBuffer.wrap(sec).order(ByteOrder.LITTLE_ENDIAN).get();

    return reader;
  }

  public BlackListRow row() {
    var ret = new BlackListRow();
    ret.order     = order;
    ret.phone     = phone;
    ret.eventTime = LocalDateTime.of(year, month, day, hour, min, sec);
    return ret;
  }

  public static int bytesSize() {
    return 21;
  }

}
