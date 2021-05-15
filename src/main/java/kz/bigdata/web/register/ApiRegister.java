package kz.bigdata.web.register;

import kz.bigdata.web.model.web.Borrower;
import kz.bigdata.web.model.web.Smartphone;

import java.util.List;

public interface ApiRegister {

  String getResult();

  Borrower getBorrower(String ctn);

  List<Borrower> getBorrowers();

  Smartphone getSmartphone(int id);

  List<Smartphone> getSmartphones();
}
