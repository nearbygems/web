package kz.bigdata.web.register;

import kz.bigdata.web.model.black_list.Borrower;

import java.util.List;

public interface ApiRegister {

  String getResult();

  List<Borrower> getBorrowers();
}
