package kz.bigdata.web.beans;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import kz.bigdata.web.config.PostgresConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceBean {

  // region Autowired fields
  @Autowired
  private PostgresConf postgresConf;
  // endregion

  @Bean
  public DataSource dataSource() {
    var conf = new HikariConfig();
    conf.setDriverClassName(postgresConf.driver());
    conf.setJdbcUrl("jdbc:postgresql://" + postgresConf.host() + ":" + postgresConf.port() + "/" + postgresConf.name());
    conf.setUsername(postgresConf.username());
    conf.setPassword(postgresConf.password());
    return new HikariDataSource(conf);
  }

}
