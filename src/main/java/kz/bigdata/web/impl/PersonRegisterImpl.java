package kz.bigdata.web.impl;

import kz.bigdata.web.dao.model.PersonDao;
import kz.bigdata.web.model.Person;
import kz.bigdata.web.register.PersonRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonRegisterImpl implements PersonRegister {

  // region Autowired fields
  @Autowired
  private PersonDao personDao;
  // endregion

  @Override
  public void savePerson(Person person) {
    personDao.save(person);
  }
}
