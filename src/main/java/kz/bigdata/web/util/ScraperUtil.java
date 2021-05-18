package kz.bigdata.web.util;

import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.stream.Stream;

public class ScraperUtil {

  private static Logger logger = LoggerFactory.getLogger(ScraperUtil.class);

  public static String getTitle(Element body) {
    var iterator = body.getElementsByClass("title").stream().iterator();
    if (iterator.hasNext()) {
      return iterator.next().text();
    }
    throw new RuntimeException("P7P6PGir0D :: No title on the smartphone page");
  }

  public static int getPrice(Element body) {
    var iterator = body.getElementsByClass("num").stream().iterator();
    if (iterator.hasNext()) {
      return Integer.parseInt(iterator.next().text().replaceAll("\\s+", ""));
    }
    throw new RuntimeException("vD1DEh7F0h :: No price on the smartphone page");
  }

  public static String getSeller(Element body) {
    var iterator = body.getElementById("card").getElementsByClass("alfa-seller").stream().iterator();
    if (iterator.hasNext()) {
      return iterator.next().val("a").text().replaceAll("Магазин", "").trim();
    }
    throw new RuntimeException("A2WWC3lplR :: No seller on the smartphone page");
  }

  public static String getRam(Element info) {
    try {
      var iterator = info.select("dt:contains(Оперативная память) + dd").stream().iterator();
      if (iterator.hasNext()) {
        return iterator.next().text();
      } else {
        return null;
      }
    } catch (Exception e) {
      logger.info("6724qtH4Tx :: ram is null");
      return null;
    }
  }

  public static String getMemory(Element info) {
    try {
      var iterator = info.select("dt:contains(Встроенная память) + dd").stream().iterator();
      if (iterator.hasNext()) {
        return iterator.next().text();
      } else {
        return null;
      }
    } catch (Exception e) {
      logger.info("P3S40a2zRS :: memory is null");
      return null;
    }
  }

  public static String pagesUrl(Element body) {
    var iterator = body.getElementsByClass("pagination").stream().iterator();
    if (iterator.hasNext()) {
      var links = iterator.next().getElementsByAttribute("href").stream().iterator();
      if (links.hasNext()) {
        return links.next().attr("href");
      }
    }
    throw new RuntimeException("ToUeco6J65 :: Web site has not pagination");
  }

  public static int pagesCount(Element body) {
    var iterator = body.getElementsByClass("pagination").stream().iterator();
    if (iterator.hasNext()) {
      var links = iterator.next().getElementsByAttribute("href").toArray();
      return Stream.of(links)
        .filter(Element.class::isInstance)
        .map(Element.class::cast)
        .map(link -> link.val("a").text())
        .filter(ScraperUtil::isNumeric)
        .map(Integer::parseInt)
        .max(Comparator.comparingInt(Integer::intValue))
        .orElse(0);
    }
    throw new RuntimeException("6F910WUh9L :: Web site has not pagination");
  }

  public static boolean isNumeric(String str) {
    if (str == null) {
      return false;
    }
    try {
      Integer.parseInt(str);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

}
