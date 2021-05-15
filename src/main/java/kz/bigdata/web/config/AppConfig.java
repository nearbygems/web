package kz.bigdata.web.config;

import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Параметры приложения")
public interface AppConfig {

  @Description("Директория для бинарных файлов")
  @DefaultStrValue("src/main/resources/blacklist/new/")
  String binNewDir();

  @Description("Директория для бинарных файлов")
  @DefaultStrValue("src/main/resources/blacklist/csv/")
  String blacklistCsvDir();

  @Description("Директория для бинарных файлов")
  @DefaultStrValue("src/main/resources/blacklist/csv/")
  String smartphonesCsvDir();

  @Description("Директория для csv файлов")
  @DefaultStrValue("src/main/resources/blacklist/migrated/")
  String binMigratedDir();

  @Description("Наименования столбцов бинарных файлов")
  @DefaultStrValue("Номер, Номер телефона, Время события")
  String blackListHeader();

}
