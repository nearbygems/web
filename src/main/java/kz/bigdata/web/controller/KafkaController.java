package kz.bigdata.web.controller;

import kz.bigdata.web.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

  private final KafkaProducer kafkaProducer;

  @Autowired
  public KafkaController(KafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  @PostMapping("/send-to-blacklist")
  public void sendToBlackList(@RequestParam("message") String message) {
    kafkaProducer.sendToBlackList(message);
  }

}
