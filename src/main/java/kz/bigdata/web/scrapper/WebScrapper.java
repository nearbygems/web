package kz.bigdata.web.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;

@Component
public class WebScrapper {

  private static final String URL = "https://alfa.kz/phones/telefony-i-smartfony";
  private static final String PREFIX = "https://alfa.kz";

  private final Queue<String> pages = new ArrayDeque<>();

  public void parseWebSites() throws IOException {

    var doc = Jsoup.connect(URL).timeout(10 * 1000).get();

    var links = doc.select("a[href]");

    var pageUrl = pagesUrl(links);

    var pagesCount = pagesCount(links);

    System.out.println(pageUrl);

    System.out.println(pagesCount);

    for (var i = 0; i <= pagesCount; i++) {
      System.out.println(PREFIX + pageUrl.replaceAll("[0-9]", String.valueOf(i)));
    }

  }

  private String pagesUrl(Elements links) {
    return links.stream()
      .filter(link -> link.attr("href").contains("page"))
      .map(link -> link.attr("href"))
      .findAny()
      .orElse("");
  }

  private int pagesCount(Elements links) {
    return links.stream()
      .filter(link -> link.attr("href").contains("page"))
      .map(link -> link.val("a").text())
      .filter(this::isNumeric)
      .map(Integer::parseInt)
      .max(Comparator.comparingInt(Integer::intValue))
      .orElse(0);
  }

  private boolean isNumeric(String str) {
    if (str == null) {
      return false;
    }
    try {
      var i = Integer.parseInt(str);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

}
