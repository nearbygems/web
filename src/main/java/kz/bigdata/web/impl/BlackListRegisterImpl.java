package kz.bigdata.web.impl;

import kz.bigdata.web.config.AppConfig;
import kz.bigdata.web.model.parse_bin.BinaryLine;
import kz.bigdata.web.model.parse_bin.BlackListRow;
import kz.bigdata.web.producer.KafkaProducer;
import kz.bigdata.web.register.BlackListRegister;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class BlackListRegisterImpl implements BlackListRegister {

  // region Autowired fields
  @Autowired
  private KafkaProducer kafkaProducer;

  @Autowired
  private AppConfig appConfig;
  // endregion

  @Override
  @SneakyThrows
  public void parseBinaryFiles() {

    var folder = new File(appConfig.binNewDir());

    for (var file : Objects.requireNonNull(folder.listFiles())) {

      try (var input = new FileInputStream(file)) {

        var rows = new ArrayList<BlackListRow>();

        var rowCount = file.length() / BinaryLine.bytesSize();

        for (var i = 0; i < rowCount; i++) {

          rows.add(BinaryLine.ofStream(input).row());

        }

        var csv = new File(csvName(file.getName()));

        try (var writer = new PrintWriter(csv)) {
          writer.println(appConfig.blackListHeader());
          rows.stream()
            .map(BlackListRow::toCsvRow)
            .forEach(writer::println);
        }

        file.delete();

        sendToKafka(csv.getPath());

      }
    }


  }

  private void sendToKafka(String path) throws IOException {
    try (var stream = Files.lines(Paths.get(path))) {
      stream.forEach(kafkaProducer::sendToBlackList);
    }
  }

  private String csvName(String fileName) {
    return appConfig.binMigratedDir() + fileName.replace(".bin", "_migrated_" + LocalDateTime.now() + ".csv");
  }

}
