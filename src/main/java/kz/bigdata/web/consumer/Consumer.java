package kz.bigdata.web.consumer;

import kz.bigdata.web.config.AppConfig;
import kz.bigdata.web.register.KafkaRegister;
import kz.bigdata.web.util.App;
import kz.bigdata.web.util.KafkaTopic;
import kz.bigdata.web.util.KafkaUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class Consumer {

  // region Autowired fields
  @Autowired
  private KafkaRegister kafkaRegister;

  @Autowired
  private AppConfig appConfig;
  // endregion

  @KafkaListener(topics = KafkaTopic.BLACKLIST, groupId = KafkaUtil.GROUP_ID)
  public void listenBlackList(byte[] message) {
    try {
      kafkaRegister.saveToBlackList(new String(message, StandardCharsets.UTF_8));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @SneakyThrows
  @KafkaListener(topics = KafkaTopic.BLACKLIST_CSV, groupId = KafkaUtil.GROUP_ID)
  public void listenBlackListCsv(byte[] message) {
    var fileName = App.appDir() + appConfig.blacklistCsvDir() + "data_" + LocalDateTime.now() + ".csv";
    try (var fos = new FileOutputStream(fileName)) {
      fos.write(message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
