package kz.bigdata.web.controller;

import kz.bigdata.web.register.BlackListRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bin")
public class ParseBinController {

  private final BlackListRegister blackListRegister;

  @Autowired
  public ParseBinController(BlackListRegister blackListRegister) {
    this.blackListRegister = blackListRegister;
  }

  @PostMapping("/parse")
  public void parseBinaryFiles() {
    blackListRegister.parseBinaryFiles();
  }

}
