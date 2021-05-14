package kz.bigdata.web.impl;

import kz.bigdata.web.dao.model.BorrowerDao;
import kz.bigdata.web.model.black_list.Borrower;
import kz.bigdata.web.register.KafkaRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaRegisterImpl implements KafkaRegister {

  @Autowired
  private BorrowerDao borrowerDao;

  @Override
  public void saveToBlackList(String row) {
    borrowerDao.save(Borrower.valueFromCsvRow(row));
  }

}
