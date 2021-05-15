package kz.bigdata.web.impl;

import kz.bigdata.web.config.AppConfig;
import kz.bigdata.web.model.parse_bin.BinaryLine;
import kz.bigdata.web.model.parse_bin.BlackListRow;
import kz.bigdata.web.producer.Producer;
import kz.bigdata.web.register.BlackListRegister;
import kz.bigdata.web.util.App;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
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
  private Producer producer;

  @Autowired
  private AppConfig appConfig;
  // endregion

  @Override
  @SneakyThrows
  public void parseBinaryFiles() {

    var folder = new File(App.appDir() + appConfig.binNewDir());

    for (var file : Objects.requireNonNull(folder.listFiles())) {

      if (file.getName().contains("migrated")) {
        continue;
      }

      try (var input = new FileInputStream(file)) {

        var rows = new ArrayList<BlackListRow>();

        var rowCount = file.length() / BinaryLine.bytesSize();

        for (var i = 0; i < rowCount; i++) {

          rows.add(BinaryLine.ofStream(input).row());

        }

        var csv = new File(csvName(file.getName()));

        try (var writer = new PrintWriter(csv)) {
          writer.println(BlackListRow.header());
          rows.stream()
            .map(BlackListRow::toCsvRow)
            .forEach(writer::println);
        }

        sendMessagesToKafka(csv.getPath());

        file.renameTo(new File(App.appDir() + appConfig.binNewDir() + file.getName().replace(".bin", "_migrated.bin")));

        producer.sendToBlackList(csv);

      }
    }

  }

  @SneakyThrows
  private void sendMessagesToKafka(String path) {
    try (var stream = Files.lines(Paths.get(path))) {
      stream.skip(1).forEach(producer::sendToBlackList);
    }
  }

  private String csvName(String fileName) {
    return App.appDir() + appConfig.binMigratedDir() + fileName.replace(".bin", "_migrated_" + LocalDateTime.now() + ".csv");
  }

}
