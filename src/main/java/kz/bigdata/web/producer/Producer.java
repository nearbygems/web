package kz.bigdata.web.producer;

import kz.bigdata.web.util.KafkaTopic;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Component
public class Producer {

  // region Autowired fields
  @Autowired
  private KafkaTemplate<String, byte[]> template;
  // endregion

  private void send(String topic, byte[] message) {
    template.send(topic, message);
  }

  public void sendToBlackList(String message) {

    send(KafkaTopic.BLACKLIST, message.getBytes(StandardCharsets.UTF_8));
  }

  public void sendToSmartPhones(String message) {
    send(KafkaTopic.SMARTPHONES, message.getBytes(StandardCharsets.UTF_8));
  }

  @SneakyThrows
  public void sendToBlackList(File file) {
    send(KafkaTopic.BLACKLIST_CSV, Files.readAllBytes(file.toPath()));
  }

  @SneakyThrows
  public void sendToSmartPhones(File file) {
    send(KafkaTopic.SMARTPHONES_CSV, Files.readAllBytes(file.toPath()));
  }

}
