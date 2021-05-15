package kz.bigdata.web.model.mongo;

import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.bson.types.ObjectId;

@ToString
@FieldNameConstants
public class SmartPhoneDto {
  public ObjectId id;
}
