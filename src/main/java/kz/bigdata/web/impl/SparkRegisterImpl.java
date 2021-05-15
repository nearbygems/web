package kz.bigdata.web.impl;

import kz.bigdata.web.register.SparkRegister;
import org.springframework.stereotype.Component;

@Component
public class SparkRegisterImpl implements SparkRegister {

  @Override
  public void saveToSmartPhones(String csv) {

    System.out.println(csv);

  }

}
