package kz.bigdata.web.config;

import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Параметры базы данных")
public interface DbConfig {

  @Description("Хост")
  @DefaultStrValue("localhost")
  String host();

  @Description("Порт")
  @DefaultIntValue(1026)
  int port();

  @Description("Наименование БД")
  @DefaultStrValue("web")
  String dbName();

  @Description("Наименование пользователя")
  @DefaultStrValue("bergen")
  String username();

  @Description("Пароль доступа к БД")
  @DefaultStrValue("bergen")
  String password();

}
