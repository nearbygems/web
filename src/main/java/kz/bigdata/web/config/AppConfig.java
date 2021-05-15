package kz.bigdata.web.config;

import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Параметры приложения")
public interface AppConfig {

  @Description("Директория для бинарных файлов")
  @DefaultStrValue("/blacklist/new/")
  String binNewDir();

  @Description("Директория для бинарных файлов")
  @DefaultStrValue("/blacklist/csv/")
  String blacklistCsvDir();

  @Description("Директория для бинарных файлов")
  @DefaultStrValue("/smartphones/csv/")
  String smartphonesCsvDir();

  @Description("Директория для csv файлов")
  @DefaultStrValue("/blacklist/migrated/")
  String binMigratedDir();

  @Description("Страница для парсинга")
  @DefaultStrValue("https://alfa.kz/phones/telefony-i-smartfony")
  String urlToParse();

  @Description("Сайт для парсинга")
  @DefaultStrValue("https://alfa.kz")
  String websiteToParse();

}
