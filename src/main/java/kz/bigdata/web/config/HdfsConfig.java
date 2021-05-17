package kz.bigdata.web.config;

import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Параметры базы данных")
public interface HdfsConfig {

  @Description("Хост")
  @DefaultStrValue("hadoop")
  String host();

  @Description("Порт")
  @DefaultIntValue(9000)
  int port();

  @Description("Хост")
  @DefaultStrValue("/tmp/bergen/")
  String folder();

}
