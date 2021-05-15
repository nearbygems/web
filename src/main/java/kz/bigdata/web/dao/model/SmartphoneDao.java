package kz.bigdata.web.dao.model;

import kz.bigdata.web.model.mongo.SmartphoneDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SmartphoneDao {

  @Insert(
    "INSERT INTO smartphones_v2 (title, price, seller, ram, memory) " +
      "VALUES (#{smartphone.title}, #{smartphone.price}, #{smartphone.seller}, #{smartphone.ram}, #{smartphone.memory})"
  )
  void save(@Param("smartphone") SmartphoneDto smartphone);

  @Select(
    "SELECT title, price, seller, ram, memory FROM smartphones_v2 WHERE id = #{id}"
  )
  SmartphoneDto loadById(@Param("id") int id);

  @Select(
    "SELECT title, price, seller, ram, memory FROM smartphones_v2"
  )
  List<SmartphoneDto> load();

  @Select(
    "SELECT price FROM smartphones WHERE id = #{id}"
  )
  int priceById(@Param("id") int id);

}
