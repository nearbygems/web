package kz.bigdata.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class WebApplicationTests {

  // region Autowired fields
  @Autowired
  private PersonRegister personRegister;

  @Autowired
  private PersonDao personDao;
  // endregion

  @Test
  void test() {

    var person = new Person();
    person.id = UUID.randomUUID().toString();
    person.name = "Bergen";

    //
    //
    personRegister.savePerson(person);
    //
    //

    var dbPerson = personDao.getPersonById(person.id);

    assertThat(dbPerson).isNotNull();
    assertThat(dbPerson.name).isEqualTo(person.name);

  }

}
