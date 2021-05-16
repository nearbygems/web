package kz.bigdata.web.scrapper;

import kz.bigdata.web.config.AppConfig;
import kz.bigdata.web.model.mongo.SmartphoneDto;
import kz.bigdata.web.producer.Producer;
import kz.bigdata.web.util.App;
import kz.bigdata.web.util.Ids;
import kz.bigdata.web.util.ScrapperUtil;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class WebScrapper {

  Logger logger = LoggerFactory.getLogger(WebScrapper.class);

  // region Autowired fields
  @Autowired
  private AppConfig appConfig;

  @Autowired
  private Producer producer;
  // endregion

  @SneakyThrows
  public void parseWebSites() {

    logger.info("X479SRJbXe :: started parsing website = `" + appConfig.urlToParse() + "`");

    var body = Jsoup.connect(appConfig.urlToParse())
      .timeout(1000000)
      .get()
      .body();

    var pagesUrl = ScrapperUtil.pagesUrl(body);

    var pagesCount = ScrapperUtil.pagesCount(body);

    for (var i = 0; i < pagesCount; i++) {
      var url = appConfig.websiteToParse() + pagesUrl.replaceAll("#", "/page" + i + "#");
      logger.info("0N25zt0qMu :: started parsing page = `" + url + "`");
      parsePage(url);
    }

  }

  @SneakyThrows
  private void parsePage(String url) {

    var page = Jsoup.connect(url).timeout(1000000).get();

    var products = page.body().getElementById("products");

    var smartphones = new ArrayList<SmartphoneDto>();

    for (var product : products.getElementsByClass("title")) {

      for (var link : product.getElementsByAttribute("href")) {

        if (!link.attr("href").contains("wishlist")) {

          var smartphonePage = link.attr("href");

          smartphones.add(parseSmartphonePage(smartphonePage));

        }

      }

    }

    var csv = new File(App.dir() + appConfig.smartphonesCsvDir() + "smartphones_" + LocalDateTime.now() + ".csv");

    logger.info("9w8cw52dfh :: started creating csv = `" + csv.getName() + "`");

    try (var writer = new PrintWriter(csv)) {
      writer.println(SmartphoneDto.header());
      smartphones.stream()
        .map(SmartphoneDto::toCsvRow)
        .peek(producer::sendToSmartphones)
        .forEach(writer::println);
    }

    logger.info("22ttRr2c55 :: page parsed with total smartphones count = `" + smartphones.size() + "`");

    producer.sendToSmartphonesCsv(csv.getPath());

  }

  @SneakyThrows
  private SmartphoneDto parseSmartphonePage(String url) {

    var page = Jsoup.connect(url).timeout(1000000).get();

    var body = page.body().getElementById("product-body");

    var info = body.getElementById("additional-info");

    var ret = new SmartphoneDto();
    ret.id = Ids.generate();
    ret.title = ScrapperUtil.getTitle(body);
    ret.price = ScrapperUtil.getPrice(body);
    ret.seller = ScrapperUtil.getSeller(body);
    ret.ram = ScrapperUtil.getRam(info);
    ret.memory = ScrapperUtil.getMemory(info);

    return ret;

  }

}
