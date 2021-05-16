package kz.bigdata.web.config;

import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Параметры базы данных")
public interface PostgresConfig {

  @Description("Хост")
  @DefaultStrValue("postgres")
  String host();

  @Description("Порт")
  @DefaultIntValue(5432)
  int port();

  @Description("Драйвер")
  @DefaultStrValue("org.postgresql.Driver")
  String driver();

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
