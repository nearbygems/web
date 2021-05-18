package kz.bigdata.web.controller;

import kz.bigdata.web.scraper.WebScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web-scraper")
public class ScraperController {

  private final WebScraper webScraper;

  @Autowired
  public ScraperController(WebScraper webScraper) {
    this.webScraper = webScraper;
  }

  @PostMapping("/parse-web-sites")
  public void parseWebSites() {
    webScraper.parseWebSites();
  }

}
