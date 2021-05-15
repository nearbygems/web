package kz.bigdata.web.impl;

import kz.bigdata.web.dao.model.BorrowerDao;
import kz.bigdata.web.dao.model.SmartphoneDao;
import kz.bigdata.web.model.mongo.BorrowerDto;
import kz.bigdata.web.model.mongo.SmartphoneDto;
import kz.bigdata.web.mongo.MongoAccess;
import kz.bigdata.web.register.KafkaRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaRegisterImpl implements KafkaRegister {

  // region Autowired fields
  @Autowired
  private SmartphoneDao smartphoneDao;

  @Autowired
  private BorrowerDao borrowerDao;

  @Autowired
  private MongoAccess mongoAccess;
  // endregion

  @Override
  public void saveToBlackList(String row) {
    var borrower = BorrowerDto.valueFromCsvRow(row);
    if (borrower == null) {
      return;
    }
    borrowerDao.save(borrower.web());
    mongoAccess.blacklist().insertOne(borrower);
  }

  @Override
  public void saveToSmartPhones(String row) {
    var smartphone = SmartphoneDto.valueFromCsvRow(row);
    if (smartphone == null) {
      return;
    }
    smartphoneDao.save(smartphone);
    mongoAccess.smartphones().insertOne(smartphone);
  }

}
