package kz.bigdata.web.config;

import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

public interface MongoConfig {

  @Description("Хост")
  @DefaultStrValue("mongo")
  String host();

  @Description("Порт")
  @DefaultIntValue(27017)
  int port();

}
