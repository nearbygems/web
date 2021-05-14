package kz.bigdata.web.impl;

import kz.bigdata.web.dao.model.BorrowerDao;
import kz.bigdata.web.model.black_list.Borrower;
import kz.bigdata.web.register.ApiRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiRegisterImpl implements ApiRegister {

  // region Autowired fields
  @Autowired
  private BorrowerDao borrowerDao;
  // endregion

  @Override
  public String getResult() {
    return null;
  }

  @Override
  public List<Borrower> getBorrowers() {
    return borrowerDao.load();
  }
}
