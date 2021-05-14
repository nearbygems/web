package kz.bigdata.web.producer;

import kz.bigdata.web.util.KafkaTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

  // region Autowired fields
  @Autowired
  private KafkaTemplate<String, String> template;
  // endregion

  private void send(String topic, String message) {
    template.send(topic, message);
  }

  public void sendToBlackList(String message) {
    send(KafkaTopic.BLACKLIST, message);
  }

  public void sendToSmartPhones(String message) {
    send(KafkaTopic.SMARTPHONES, message);
  }

}
