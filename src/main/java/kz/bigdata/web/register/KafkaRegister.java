package kz.bigdata.web.register;

public interface KafkaRegister {

  void saveToBlackList(String row);

  void saveToSmartPhones(String row);

}
