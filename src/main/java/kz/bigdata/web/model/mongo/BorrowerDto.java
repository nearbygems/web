package kz.bigdata.web.model.mongo;

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
}
