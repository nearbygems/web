package kz.bigdata.web.consumer;

import kz.bigdata.web.config.AppConfig;
import kz.bigdata.web.producer.Producer;
import kz.bigdata.web.register.KafkaRegister;
import kz.bigdata.web.register.SparkRegister;
import kz.bigdata.web.util.App;
import kz.bigdata.web.util.KafkaTopic;
import kz.bigdata.web.util.KafkaUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class Consumer {

  Logger logger = LoggerFactory.getLogger(Producer.class);

  // region Autowired fields
  @Autowired
  private SparkRegister sparkRegister;

  @Autowired
  private KafkaRegister kafkaRegister;

  @Autowired
  private AppConfig appConfig;
  // endregion

  @KafkaListener(topics = KafkaTopic.BLACKLIST, groupId = KafkaUtil.GROUP_ID)
  public void listenBlackList(byte[] message) {
    try {
      var string = new String(message, StandardCharsets.UTF_8);
      kafkaRegister.saveToBlackList(string);
      logger.info("bj95xmQhna :: message = `" + string + "` saved to blacklist");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  @SneakyThrows
  @KafkaListener(topics = KafkaTopic.BLACKLIST_CSV, groupId = KafkaUtil.GROUP_ID)
  public void listenBlackListCsv(byte[] message) {
    var fileName = App.dir() + appConfig.blacklistCsvDir() + "data_" + LocalDateTime.now() + ".csv";
    try (var fos = new FileOutputStream(fileName)) {
      fos.write(message);
      logger.info("T97T7s6dGK :: `" + fileName + "` saved in filestorage");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  @KafkaListener(topics = KafkaTopic.SMARTPHONES, groupId = KafkaUtil.GROUP_ID)
  public void listenSmartphones(byte[] message) {
    try {
      var string = new String(message, StandardCharsets.UTF_8);
      kafkaRegister.saveToSmartPhones(string);
      logger.info("t0dpj7KS7u :: message = `" + string + "` saved to smartphones");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  @KafkaListener(topics = KafkaTopic.SMARTPHONES_CSV, groupId = KafkaUtil.GROUP_ID)
  public void listenSmartphonesCsv(byte[] message) {
    try {
      var string = new String(message, StandardCharsets.UTF_8);
      sparkRegister.saveToSmartPhones(string);
      logger.info("5L7FnvP8O9 :: csv file = `" + string + "` send to spark");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

}
