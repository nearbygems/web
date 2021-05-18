package kz.bigdata.web.config;

import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Параметры mongo")
public interface MongoConf {

  @Description("Хост")
  @DefaultStrValue("mongo")
  String host();

  @Description("Порт")
  @DefaultIntValue(27017)
  int port();

}
