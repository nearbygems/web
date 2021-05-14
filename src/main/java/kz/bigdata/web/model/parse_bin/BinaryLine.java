package kz.bigdata.web.model.parse_bin;

import lombok.ToString;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@ToString
public class BinaryLine {

  public int order;
  public String phone;
  public int year;
  public int month;
  public int day;
  public int hour;
  public int min;
  public int sec;

  public static BinaryLine ofStream(FileInputStream input) throws IOException {
    var reader = new BinaryLine();

    var order = new byte[4];
    input.read(order, 0, order.length);
    reader.order = ByteBuffer.wrap(order).order(ByteOrder.BIG_ENDIAN).getInt();

    var phone = new byte[10];
    input.read(phone, 0, phone.length);
    reader.phone = new String(ByteBuffer.wrap(phone).array(), StandardCharsets.UTF_8);

    var year = new byte[2];
    input.read(year, 0, year.length);
    reader.year = ByteBuffer.wrap(year).order(ByteOrder.LITTLE_ENDIAN).getShort();

    var month = new byte[1];
    input.read(month, 0, month.length);
    reader.month = ByteBuffer.wrap(month).order(ByteOrder.LITTLE_ENDIAN).get();

    var day = new byte[1];
    input.read(day, 0, day.length);
    reader.day = ByteBuffer.wrap(day).order(ByteOrder.LITTLE_ENDIAN).get();

    var hour = new byte[1];
    input.read(hour, 0, hour.length);
    reader.hour = ByteBuffer.wrap(hour).order(ByteOrder.LITTLE_ENDIAN).get();

    var min = new byte[1];
    input.read(min, 0, min.length);
    reader.min = ByteBuffer.wrap(min).order(ByteOrder.LITTLE_ENDIAN).get();

    var sec = new byte[1];
    input.read(sec, 0, sec.length);
    reader.sec = ByteBuffer.wrap(sec).order(ByteOrder.LITTLE_ENDIAN).get();

    return reader;
  }

  public BlackListRow row() {
    var ret = new BlackListRow();
    ret.order = order;
    ret.phone = phone;
    ret.time = LocalDateTime.of(year, month, day, hour, min, sec);
    return ret;
  }

  public static int bytesSize() {
    return 21;
  }

}
