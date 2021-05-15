package kz.bigdata.web.beans;

import kz.bigdata.web.config.AppConfig;
import kz.bigdata.web.config.MongoConfig;
import kz.bigdata.web.config.PostgresConfig;
import kz.bigdata.web.config.kafka.KafkaConfig;
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
  public AppConfig appConfig() {
    return createConfig(AppConfig.class);
  }

  @Bean
  public PostgresConfig dbConfig() {
    return createConfig(PostgresConfig.class);
  }

  @Bean
  public MongoConfig mongoConfig() {
    return createConfig(MongoConfig.class);
  }

  @Bean
  public KafkaConfig kafkaConfig() {
    return createConfig(KafkaConfig.class);
  }

}
