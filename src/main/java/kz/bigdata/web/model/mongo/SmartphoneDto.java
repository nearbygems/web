package kz.bigdata.web.model.mongo;

import kz.bigdata.web.model.web.Smartphone_v2;
import kz.bigdata.web.util.Ids;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.bson.types.ObjectId;

@ToString
@FieldNameConstants
public class SmartphoneDto {

  public ObjectId id;
  public String title;
  public int price;
  public String seller;
  public String ram;
  public String memory;

  public static String header() {
    return Fields.title + ";" + Fields.price + ";" + Fields.seller + ";" + Fields.ram + ";" + Fields.memory;
  }

  public String toCsvRow() {
    return title + ";" + price + ";" + seller + ";" + ram + ";" + memory;
  }

  public static SmartphoneDto valueFromCsvRow(String row) {

    var values = row.trim().split(";");

    if (values.length != 5) {
      return null;
    }

    var ret = new SmartphoneDto();

    ret.id = Ids.generate();
    ret.title = values[0].trim();
    ret.price = Integer.parseInt(values[1].trim());
    ret.seller = values[2].trim();
    ret.ram = values[3].trim();
    ret.memory = values[4].trim();

    return ret;
  }

  public Smartphone_v2 web() {
    var ret = new Smartphone_v2();
    ret.title = title;
    ret.price = price;
    ret.seller = seller;
    ret.ram = ram;
    ret.memory = memory;
    return ret;
  }

  public String strId() {
    return Ids.toStrId(id);
  }

}
