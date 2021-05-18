package kz.bigdata.web.impl;

import kz.bigdata.web.config.AppConf;
import kz.bigdata.web.model.parse_bin.BinaryLine;
import kz.bigdata.web.model.parse_bin.BlackListRow;
import kz.bigdata.web.producer.Producer;
import kz.bigdata.web.register.BlackListRegister;
import kz.bigdata.web.util.App;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private final Logger logger = LoggerFactory.getLogger(BlackListRegister.class);

  // region Autowired fields
  @Autowired
  private Producer producer;

  @Autowired
  private AppConf appConf;
  // endregion

  @Override
  @SneakyThrows
  public void parseBinaryFiles() {

    var folder = new File(App.dir() + appConf.binNewDir());

    logger.info("f527jp0JBr :: started parsing files in the folder = `" + folder.getName() + "`");

    for (var file : Objects.requireNonNull(folder.listFiles())) {

      if (file.getName().contains("migrated")) {
        continue;
      }

      logger.info("fbB4i4648l :: started parsing file = `" + file.getName() + "`");

      try (var input = new FileInputStream(file)) {

        var rows = new ArrayList<BlackListRow>();

        var rowCount = file.length() / BinaryLine.bytesSize();

        for (var i = 0; i < rowCount; i++) {

          rows.add(BinaryLine.ofStream(input).row());

        }

        var csv = new File(csvName(file.getName()));

        logger.info("8f46sGrL3h :: started creating csv file = `" + csv.getName() + "`");

        try (var writer = new PrintWriter(csv)) {
          writer.println(BlackListRow.header());
          rows.stream()
              .map(BlackListRow::toCsvRow)
              .forEach(writer::println);
        }

        sendMessagesToKafka(csv.getPath());

        file.renameTo(new File(App.dir() + appConf.binNewDir() + file.getName().replace(".bin", "_migrated.bin")));

        producer.sendToBlackList(csv);

      }

      logger.info("X3V4a8n86w :: file = `" + file.getName() + "` had been parsed");

    }

    logger.info("2Y6Ta4i6w8 :: all files had been parsed");

  }

  @SneakyThrows
  private void sendMessagesToKafka(String path) {
    try (var stream = Files.lines(Paths.get(path))) {
      stream.skip(1).forEach(producer::sendToBlackList);
    }
  }

  private String csvName(String fileName) {
    return App.dir() + appConf.binMigratedDir() + fileName.replace(".bin", "_migrated_" + LocalDateTime.now() + ".csv");
  }

}
