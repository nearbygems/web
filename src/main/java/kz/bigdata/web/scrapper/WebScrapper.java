package kz.bigdata.web.scrapper;

import kz.bigdata.web.config.AppConfig;
import kz.bigdata.web.model.mongo.SmartphoneDto;
import kz.bigdata.web.util.Ids;
import kz.bigdata.web.util.ScrapperUtil;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebScrapper {

  // region Autowired fields
  @Autowired
  private AppConfig appConfig;
  // endregion

  @SneakyThrows
  public void parseWebSites() {

    var body = Jsoup.connect(appConfig.urlToParse())
      .timeout(1000000)
      .get()
      .body();

    var pagesUrl = ScrapperUtil.pagesUrl(body);

    var pagesCount = ScrapperUtil.pagesCount(body);

    for (var i = 0; i < pagesCount; i++) {
      var url = appConfig.websiteToParse() + pagesUrl.replaceAll("#", "/page" + i + "#");
      parsePage(url);
    }

  }

  @SneakyThrows
  private void parsePage(String url) {

    var page = Jsoup.connect(url).timeout(1000000).get();

    var products = page.body().getElementById("products");

    for (var product : products.getElementsByClass("title")) {

      for (var link : product.getElementsByAttribute("href")) {

        if (!link.attr("href").contains("wishlist")) {

          var smartphonePage = link.attr("href");

          var smartphone = parseSmartphonePage(smartphonePage);

        }

      }

    }

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
