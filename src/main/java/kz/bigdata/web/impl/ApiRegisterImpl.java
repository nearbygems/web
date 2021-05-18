package kz.bigdata.web.impl;

import kz.bigdata.web.dao.model.BorrowerDao;
import kz.bigdata.web.dao.model.LogDao;
import kz.bigdata.web.dao.model.SmartphoneDao;
import kz.bigdata.web.dao.model.Smartphone_v2_Dao;
import kz.bigdata.web.model.mongo.SmartphoneDto;
import kz.bigdata.web.model.web.Borrower;
import kz.bigdata.web.model.web.Client;
import kz.bigdata.web.model.web.ReasonType;
import kz.bigdata.web.model.web.Rejection;
import kz.bigdata.web.model.web.Result;
import kz.bigdata.web.model.web.Smartphone;
import kz.bigdata.web.model.web.Smartphone_v2;
import kz.bigdata.web.register.ApiRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiRegisterImpl implements ApiRegister {

  private final Logger logger = LoggerFactory.getLogger(ApiRegister.class);

  // region Autowired fields
  @Autowired
  private Smartphone_v2_Dao smartphoneV2Dao;

  @Autowired
  private SmartphoneDao smartphoneDao;

  @Autowired
  private BorrowerDao borrowerDao;

  @Autowired
  private LogDao logDao;
  // endregion

  @Override
  public Result getResult(Client client) {

    logger.info("fhhKjpDxFy :: get result for client = `" + client.toString() + "`");

    var borrower = borrowerDao.loadByCtn(client.ctn);

    if (borrower != null) {
      logger.info("5wn1NKcimb :: client with ctn =`" + client.ctn + "` in blacklist");
      var rejection = Rejection.of(ReasonType.BLACKLIST);
      logDao.rejection(rejection, client.ctn, LocalDateTime.now());
      return rejection;
    }

    var smartPhonePrice = smartphoneDao.priceById(client.smartphoneId);

    if (smartPhonePrice == null) {
      logger.info("e9S09DlsC2 :: there is no smartphone with id =`" + client.smartphoneId + "`");
      var rejection = Rejection.of(ReasonType.NO_SMARTPHONE);
      logDao.rejection(rejection, client.ctn, LocalDateTime.now());
      return rejection;
    }

    var payment = BigDecimal.valueOf(smartPhonePrice)
                            .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);

    if (payment.compareTo(BigDecimal.valueOf(client.income).multiply(BigDecimal.valueOf(0.2))) >= 0) {
      logger.info("w85Xx0WF3f :: client income =`" + client.income + "` is too low");
      var rejection = Rejection.of(ReasonType.LOW_INCOME);
      logDao.rejection(rejection, client.ctn, LocalDateTime.now());
      return rejection;
    }

    logger.info("EDcz9716UV :: client request is approved");
    var result = Result.approved();
    logDao.result(result, client.ctn, LocalDateTime.now());
    return result;
  }

  @Override
  public Borrower getBorrower(String ctn) {
    var borrower = borrowerDao.loadByCtn(ctn);
    logger.info("012aZv6m1O :: get borrower = `" + borrower.toString() + "`");
    return borrower;
  }

  @Override
  public List<Borrower> getBorrowers() {
    var borrowers = borrowerDao.load();
    logger.info("XCClnyfTct :: get borrowers = `" + borrowers.toString() + "`");
    return borrowerDao.load();
  }

  @Override
  public Smartphone_v2 getSmartphone_v2(int id) {
    var smartphone_v2 = smartphoneV2Dao.loadById(id);
    if (smartphone_v2 == null) {
      return null;
    }
    logger.info("QGZBjlfXPm :: get smartphone = `" + smartphone_v2.web().toString() + "`");
    return smartphone_v2.web();
  }

  @Override
  public List<Smartphone_v2> getSmartphones_v2() {
    var smartphones = smartphoneV2Dao.load().stream()
                                     .map(SmartphoneDto::web)
                                     .collect(Collectors.toUnmodifiableList());
    logger.info("L4GGAR26G6 :: get smartphones = `" + smartphones.toString() + "`");
    return smartphones;
  }

  @Override
  public Smartphone getSmartphone(int id) {
    var smartphone = smartphoneDao.loadById(id);
    logger.info("p8e9It3V1J :: get smartphones = `" + smartphone.toString() + "`");
    return smartphone;
  }

  @Override
  public List<Smartphone> getSmartphones() {
    var smartphones = smartphoneDao.load();
    logger.info("AGpz8666dP :: get smartphones = `" + smartphones.toString() + "`");
    return smartphones;
  }

}
