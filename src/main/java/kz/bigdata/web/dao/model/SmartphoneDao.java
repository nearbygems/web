package kz.bigdata.web.dao.model;

import kz.bigdata.web.model.web.Smartphone;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SmartphoneDao {

  @Insert(
    "INSERT INTO smartphones (brand, model, price) " +
      "VALUES (#{smartphone.brand}, #{smartphone.model}, #{smartphone.price})"
  )
  void save(@Param("smartphone") Smartphone smartphone);

  @Select(
    "SELECT id, brand, model, price FROM smartphones WHERE id = #{id}"
  )
  Smartphone loadById(@Param("id") int id);

  @Select(
    "SELECT id, brand, model, price FROM smartphones"
  )
  List<Smartphone> load();

  @Select(
    "SELECT price FROM smartphones WHERE id = #{id}"
  )
  Integer priceById(@Param("id") int id);

}
