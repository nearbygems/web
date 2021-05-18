package kz.bigdata.web.config.kafka;

import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

public interface KafkaConf {

  @Description("Хост")
  @DefaultStrValue("kafka")
  String host();

  @Description("Порт")
  @DefaultIntValue(9092)
  int port();

}
