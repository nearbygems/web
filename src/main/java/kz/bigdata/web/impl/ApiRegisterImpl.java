package kz.bigdata.web.impl;

import kz.bigdata.web.dao.model.BorrowerDao;
import kz.bigdata.web.dao.model.SmartphoneDao;
import kz.bigdata.web.model.mongo.SmartphoneDto;
import kz.bigdata.web.model.web.*;
import kz.bigdata.web.register.ApiRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
  public Result getResult(Client client) {

    var borrower = borrowerDao.loadByCtn(client.ctn);

    if (borrower != null) {
      return Rejection.of(ReasonType.BLACKLIST);
    }

    var payment = BigDecimal.valueOf(smartphoneDao.priceById(client.smartphoneId))
      .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);

    if (payment.compareTo(BigDecimal.valueOf(client.income).multiply(BigDecimal.valueOf(0.2))) >= 0) {
      return Rejection.of(ReasonType.LOW_INCOME);
    }

    return Result.approved();
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
