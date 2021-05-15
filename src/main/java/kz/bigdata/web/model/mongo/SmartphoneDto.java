package kz.bigdata.web.model.mongo;

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

  public String strId() {
    return Ids.toStrId(id);
  }

}
