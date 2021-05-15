package kz.bigdata.web.impl;

import kz.bigdata.web.dao.model.BorrowerDao;
import kz.bigdata.web.dao.model.SmartphoneDao;
import kz.bigdata.web.model.mongo.SmartphoneDto;
import kz.bigdata.web.model.web.Borrower;
import kz.bigdata.web.model.web.Smartphone;
import kz.bigdata.web.register.ApiRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiRegisterImpl implements ApiRegister {

  // region Autowired fields
  @Autowired
  private SmartphoneDao smartphoneDao;

  @Autowired
  private BorrowerDao borrowerDao;
  // endregion

  @Override
  public String getResult() {
    return null;
  }

  @Override
  public Borrower getBorrower(String ctn) {
    return borrowerDao.loadByCtn(ctn);
  }

  @Override
  public List<Borrower> getBorrowers() {
    return borrowerDao.load();
  }

  @Override
  public Smartphone getSmartphone(int id) {
    return smartphoneDao.loadById(id).web();
  }

  @Override
  public List<Smartphone> getSmartphones() {
    return smartphoneDao.load().stream()
      .map(SmartphoneDto::web)
      .collect(Collectors.toUnmodifiableList());
  }

}
