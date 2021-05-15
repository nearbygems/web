package kz.bigdata.web.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

  public static Path dir() {
    return Paths.get(System.getProperty("user.home")).resolve(src() + ".d");
  }

  public static String src() {
    return "big_data";
  }

}
