package kz.bigdata.web.register;

import kz.bigdata.web.model.web.Borrower;
import kz.bigdata.web.model.web.Client;
import kz.bigdata.web.model.web.Result;
import kz.bigdata.web.model.web.Smartphone;

import java.util.List;

public interface ApiRegister {

  Result getResult(Client client);

  Borrower getBorrower(String ctn);

  List<Borrower> getBorrowers();

  Smartphone getSmartphone(int id);

  List<Smartphone> getSmartphones();
}
