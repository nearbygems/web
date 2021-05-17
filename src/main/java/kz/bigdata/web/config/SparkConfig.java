package kz.bigdata.web.config;

import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Параметры базы данных")
public interface SparkConfig {

  @Description("Хост")
  @DefaultStrValue("spark-master")
  String host();

  @Description("Порт")
  @DefaultIntValue(7077)
  int port();

}
