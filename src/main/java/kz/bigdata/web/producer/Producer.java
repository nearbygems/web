package kz.bigdata.web.producer;

import kz.bigdata.web.util.KafkaTopic;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Component
public class Producer {

  Logger logger = LoggerFactory.getLogger(Producer.class);

  // region Autowired fields
  @Autowired
  private KafkaTemplate<String, byte[]> template;
  // endregion

  private void send(String topic, byte[] message) {
    template.send(topic, message);
  }

  public void sendToBlackList(String message) {
    logger.info("ctXU9r5B6N :: message =`" + message + " sent to BLACKLIST");
    send(KafkaTopic.BLACKLIST, message.getBytes(StandardCharsets.UTF_8));
  }

  public void sendToSmartphones(String message) {
    logger.info("XfAXX67181 :: message =`" + message + " sent to SMARTPHONES");
    send(KafkaTopic.SMARTPHONES, message.getBytes(StandardCharsets.UTF_8));
  }

  public void sendToSmartphonesCsv(String csv) {
    logger.info("xvElf3ZF9M :: csv file name =`" + csv + " sent to SMARTPHONES_CSV");
    send(KafkaTopic.SMARTPHONES_CSV, csv.getBytes(StandardCharsets.UTF_8));
  }

  @SneakyThrows
  public void sendToBlackList(File file) {
    logger.info("qrmJmD722h :: csv file =`" + file.getName() + " sent to BLACKLIST_CSV");
    send(KafkaTopic.BLACKLIST_CSV, Files.readAllBytes(file.toPath()));
  }

}
