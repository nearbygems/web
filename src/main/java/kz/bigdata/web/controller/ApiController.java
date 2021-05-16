package kz.bigdata.web.controller;

import kz.bigdata.web.model.web.*;
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

  @PostMapping("/smartphone_v2")
  public Smartphone_v2 getSmartphone_v2(@RequestParam("id") int id) {
    return apiRegister.getSmartphone_v2(id);
  }

  @PostMapping("/smartphones_v2")
  public List<Smartphone_v2> getSmartphones_v2() {
    return apiRegister.getSmartphones_v2();
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
