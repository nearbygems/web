package kz.bigdata.web.sheduler;

import kz.bigdata.web.scrapper.WebScrapper;
import kz.greetgo.scheduling.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScrapperScheduler {

  // region Autowired fields
  @Autowired
  private WebScrapper webScrapper;
  // endregion

  @Scheduled("repeat every 10 minutes")
  public void parseWebSites() throws IOException {
    webScrapper.parseWebSites();
  }

}
