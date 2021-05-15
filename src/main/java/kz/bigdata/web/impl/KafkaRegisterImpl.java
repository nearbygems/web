package kz.bigdata.web.impl;

import kz.bigdata.web.dao.model.BorrowerDao;
import kz.bigdata.web.model.black_list.Borrower;
import kz.bigdata.web.mongo.MongoAccess;
import kz.bigdata.web.register.KafkaRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaRegisterImpl implements KafkaRegister {

  // region Autowired fields
  @Autowired
  private BorrowerDao borrowerDao;

  @Autowired
  private MongoAccess mongoAccess;
  // endregion

  @Override
  public void saveToBlackList(String row) {
    var borrower = Borrower.valueFromCsvRow(row);
    if (borrower == null) {
      return;
    }
    borrowerDao.save(borrower);
    mongoAccess.blacklist().insertOne(borrower.toDto());
  }

}
