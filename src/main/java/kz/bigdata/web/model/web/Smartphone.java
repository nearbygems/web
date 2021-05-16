package kz.bigdata.web.model.web;

import kz.bigdata.web.model.mongo.SmartphoneDto;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ToString
public class Smartphone {

  public int id;
  public String title;
  public String brand;
  public String model;
  public int price;

  public static Smartphone from(SmartphoneDto smartphoneDto) {
    var ret = new Smartphone();

    var title = smartphoneDto.title.trim().split(".(?i)gb");

    if (title.length == 0) {
      return null;
    }

    var brandAndModel = title[0].trim().split("\\s+");

    if (brandAndModel.length < 2) {
      return null;
    }

    brandAndModel[brandAndModel.length - 1] = "";

    ret.brand = brandAndModel[0].trim();
    ret.model = Stream.of(brandAndModel).skip(1).collect(Collectors.joining(" ")).trim();
    ret.price = smartphoneDto.price;
    ret.title = ret.brand + " " + ret.model;
    return ret;
  }

  public static Smartphone average(List<Smartphone> smartphones) {

    if (smartphones.isEmpty()) {
      return null;
    }

    var ret = smartphones.get(0);
    smartphones.stream().mapToInt(s -> s.price).average().ifPresent(price -> ret.price = (int) price);
    return ret;
  }

}
