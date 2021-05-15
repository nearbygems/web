package kz.bigdata.web.dao.model;

import kz.bigdata.web.model.web.Borrower;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BorrowerDao {

  @Insert(
    "INSERT INTO blacklist (ctn, event_time) VALUES (#{borrower.ctn}, #{borrower.eventTime})"
  )
  void save(@Param("borrower") Borrower borrower);

  @Select(
    "SELECT * FROM blacklist WHERE ctn = #{ctn}"
  )
  Borrower loadByCtn(@Param("ctn") String ctn);

  @Select(
    "SELECT * FROM blacklist"
  )
  List<Borrower> load();

}
