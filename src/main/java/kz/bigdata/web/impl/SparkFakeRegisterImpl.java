package kz.bigdata.web.impl;

import kz.bigdata.web.dao.model.SmartphoneDao;
import kz.bigdata.web.model.mongo.SmartphoneDto;
import kz.bigdata.web.model.web.Smartphone;
import kz.bigdata.web.register.SparkRegister;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SparkFakeRegisterImpl implements SparkRegister {

  // region Autowired fields
  @Autowired
  private SmartphoneDao smartphoneDao;
  // endregion

  @Override
  @SneakyThrows
  public void saveToSmartPhones(String csv) {

    try (var stream = Files.lines(Paths.get(csv))) {

      var smartphones = stream.skip(1)
        .map(SmartphoneDto::valueFromCsvRow)
        .filter(Objects::nonNull)
        .map(Smartphone::from)
        .collect(Collectors.groupingBy(smartphone -> smartphone.title));

      smartphones.values().stream()
        .map(Smartphone::average)
        .forEach(smartphoneDao::save);

    }

  }

}
