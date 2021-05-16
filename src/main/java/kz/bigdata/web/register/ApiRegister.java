package kz.bigdata.web.register;

import kz.bigdata.web.model.web.*;

import java.util.List;

public interface ApiRegister {

  Result getResult(Client client);

  Borrower getBorrower(String ctn);

  List<Borrower> getBorrowers();

  Smartphone_v2 getSmartphone_v2(int id);

  List<Smartphone_v2> getSmartphones_v2();

  Smartphone getSmartphone(int id);

  List<Smartphone> getSmartphones();

}
