package kz.bigdata.web.controller;

import kz.bigdata.web.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

  private final Producer producer;

  @Autowired
  public KafkaController(Producer producer) {
    this.producer = producer;
  }

  @PostMapping("/send-to-blacklist")
  public void sendToBlackList(@RequestParam("message") String message) {
    producer.sendToBlackList(message);
  }

}
