package kz.bigdata.web.sheduler;

import kz.bigdata.web.register.BlackListRegister;
import kz.greetgo.scheduling.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlackListScheduler {

  // region Autowired fields
  @Autowired
  private BlackListRegister blackListRegister;
  // endregion

  @Scheduled("repeat every 10 minutes")
  public void parseBinaryFiles() {
    blackListRegister.parseBinaryFiles();
  }

}
