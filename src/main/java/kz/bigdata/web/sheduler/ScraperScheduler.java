package kz.bigdata.web.sheduler;

import kz.bigdata.web.config.AppConf;
import kz.bigdata.web.scraper.WebScraper;
import kz.bigdata.web.util.App;
import kz.greetgo.scheduling.HasScheduled;
import kz.greetgo.scheduling.Scheduled;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ScraperScheduler implements HasScheduled {

  // region Autowired fields
  @Autowired
  private WebScraper webScraper;

  @Autowired
  private AppConf appConf;
  // endregion

  @SneakyThrows
  @Scheduled("01:00")
  public void parseWebSites() {
    webScraper.parseWebSites();
  }

  @SneakyThrows
  @PostConstruct
  private void checkDirectory() {

    var binNewDir       = new File(App.dir() + appConf.binNewDir());
    var blacklistCsvDir = new File(App.dir() + appConf.blacklistCsvDir());
    var binMigratedDir  = new File(App.dir() + appConf.binMigratedDir());

    var smartphonesCsvDir = new File(App.dir() + appConf.smartphonesCsvDir());

    if (!binNewDir.exists()) {
      if (binNewDir.mkdirs()) {
        Files.copy(
          Paths.get("src/main/resources/data.bin"),
          Paths.get(App.dir() + appConf.binNewDir() + "data.bin")
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
