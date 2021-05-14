package kz.bigdata.web.dao.model;

import kz.bigdata.web.model.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PersonDao {

  @Insert(
    "INSERT INTO persons " +
      "(id, name) " +
      "VALUES " +
      "(#{person.id}, #{person.name}) " +
      "ON CONFLICT (id) DO " +
      "UPDATE SET " +
      "name = #{person.name}"
  )
  void save(@Param("person") Person person);

  @Select("select * from persons where id = #{id}")
  Person getPersonById(@Param("id") String id);


}
