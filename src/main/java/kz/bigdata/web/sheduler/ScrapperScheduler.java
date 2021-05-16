package kz.bigdata.web.sheduler;

import kz.bigdata.web.config.AppConfig;
import kz.bigdata.web.scrapper.WebScrapper;
import kz.bigdata.web.util.App;
import kz.greetgo.scheduling.Scheduled;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ScrapperScheduler {

  // region Autowired fields
  @Autowired
  private WebScrapper webScrapper;

  @Autowired
  private AppConfig appConfig;
  // endregion

  @SneakyThrows
  @Scheduled("01:00")
  public void parseWebSites() {
    webScrapper.parseWebSites();
  }

  @SneakyThrows
  @PostConstruct
  private void checkDirectory() {

    var binNewDir = new File(App.dir() + appConfig.binNewDir());
    var blacklistCsvDir = new File(App.dir() + appConfig.blacklistCsvDir());
    var binMigratedDir = new File(App.dir() + appConfig.binMigratedDir());

    var smartphonesCsvDir = new File(App.dir() + appConfig.smartphonesCsvDir());

    if (!binNewDir.exists()) {
      if (binNewDir.mkdirs()) {
        Files.copy(
          Paths.get("src/main/resources/data.bin"),
          Paths.get(App.dir() + appConfig.binNewDir() + "data.bin")
        );
      }
    }

    if (!blacklistCsvDir.exists()) {
      blacklistCsvDir.mkdirs();
    }

    if (!binMigratedDir.exists()) {
      binMigratedDir.mkdirs();
    }

    if (!smartphonesCsvDir.exists()) {
      smartphonesCsvDir.mkdirs();
    }


  }

}
