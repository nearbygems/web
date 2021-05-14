package kz.bigdata.web.beans;

import kz.bigdata.web.config.AppConfig;
import kz.bigdata.web.config.DbConfig;
import kz.bigdata.web.util.App;
import kz.greetgo.conf.hot.FileConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class HotConfigFactory extends FileConfigFactory {

  @Override
  protected Path getBaseDir() {
    return App.appDir().resolve("config");
  }

  @Bean
  public AppConfig appConfig() {
    return createConfig(AppConfig.class);
  }

  @Bean
  public DbConfig dbConfig() {
    return createConfig(DbConfig.class);
  }

}
