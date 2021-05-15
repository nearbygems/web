package kz.bigdata.web.controller;

import kz.bigdata.web.model.web.Borrower;
import kz.bigdata.web.model.web.Client;
import kz.bigdata.web.model.web.Result;
import kz.bigdata.web.model.web.Smartphone;
import kz.bigdata.web.register.ApiRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

  private final ApiRegister apiRegister;

  @Autowired
  public ApiController(ApiRegister apiRegister) {
    this.apiRegister = apiRegister;
  }

  @PostMapping("/get-result")
  public Result getResult(@RequestBody Client client) {

    return apiRegister.getResult(client);
  }

  @PostMapping("/borrower")
  public Borrower getBorrower(@RequestParam("ctn") String ctn) {
    return apiRegister.getBorrower(ctn);
  }

  @PostMapping("/borrowers")
  public List<Borrower> getBorrowers() {
    return apiRegister.getBorrowers();
  }

  @PostMapping("/smartphone")
  public Smartphone getSmartphone(@RequestParam("id") int id) {
    return apiRegister.getSmartphone(id);
  }

  @PostMapping("/smartphones")
  public List<Smartphone> getSmartphones() {
    return apiRegister.getSmartphones();
  }

}
