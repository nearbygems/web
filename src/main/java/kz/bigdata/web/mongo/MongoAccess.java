package kz.bigdata.web.mongo;

import com.mongodb.client.MongoCollection;
import kz.bigdata.web.model.mongo.BorrowerDto;
import kz.bigdata.web.model.mongo.SmartphoneDto;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoAccess implements InitializingBean {

  // region Autowired fields
  @Autowired
  private MongoConnection mongoConnection;
  // endregion

  private MongoCollection<SmartphoneDto> smartphones;
  private MongoCollection<BorrowerDto> blacklist;

  @Override
  public void afterPropertiesSet() {

    smartphones = getCollection(SmartphoneDto.class);
    blacklist = getCollection(BorrowerDto.class);

  }

  private <T> MongoCollection<T> getCollection(Class<T> aClass) {
    return mongoConnection.database().getCollection(aClass.getSimpleName(), aClass);
  }

  public MongoCollection<SmartphoneDto> smartphones() {
    return smartphones;
  }

  public MongoCollection<BorrowerDto> blacklist() {
    return blacklist;
  }

}
