package kz.bigdata.web.beans;

import kz.bigdata.web.config.AppConf;
import kz.bigdata.web.config.MongoConf;
import kz.bigdata.web.config.PostgresConf;
import kz.bigdata.web.config.kafka.KafkaConf;
import kz.bigdata.web.util.App;
import kz.greetgo.conf.hot.FileConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class HotConfigFactory extends FileConfigFactory {

  @Override
  protected Path getBaseDir() {
    return App.dir().resolve("config");
  }

  @Bean
  public AppConf appConfig() {
    return createConfig(AppConf.class);
  }

  @Bean
  public PostgresConf dbConfig() {
    return createConfig(PostgresConf.class);
  }

  @Bean
  public MongoConf mongoConfig() {
    return createConfig(MongoConf.class);
  }

  @Bean
  public KafkaConf kafkaConfig() {
    return createConfig(KafkaConf.class);
  }

}
