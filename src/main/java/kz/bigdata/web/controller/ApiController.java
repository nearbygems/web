package kz.bigdata.web.controller;

import kz.bigdata.web.model.black_list.Borrower;
import kz.bigdata.web.register.ApiRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

  private final ApiRegister apiRegister;

  @Autowired
  public ApiController(ApiRegister apiRegister) {
    this.apiRegister = apiRegister;
  }

  @PostMapping("/result")
  public String getResult() {
    return apiRegister.getResult();
  }

  @PostMapping("/borrowers")
  public List<Borrower> getBorrowers() {
    return apiRegister.getBorrowers();
  }

}
