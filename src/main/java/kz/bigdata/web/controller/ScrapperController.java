package kz.bigdata.web.controller;

import kz.bigdata.web.scrapper.WebScrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/web-scrapper")
public class ScrapperController {

  private final WebScrapper webScrapper;

  @Autowired
  public ScrapperController(WebScrapper webScrapper) {
    this.webScrapper = webScrapper;
  }

  @PostMapping("/parse-web-sites")
  public void parseWebSites() throws IOException {
    webScrapper.parseWebSites();
  }

}
