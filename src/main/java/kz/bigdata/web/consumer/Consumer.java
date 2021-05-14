package kz.bigdata.web.consumer;

import kz.bigdata.web.register.KafkaRegister;
import kz.bigdata.web.util.KafkaTopic;
import kz.bigdata.web.util.KafkaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

  // region Autowired fields
  @Autowired
  private KafkaRegister kafkaRegister;
  // endregion

  @KafkaListener(topics = KafkaTopic.BLACKLIST, groupId = KafkaUtil.GROUP_ID)
  public void listenBlackList(String message) {
    kafkaRegister.saveToBlackList(message);
  }

}
