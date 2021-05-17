package kz.bigdata.web.beans;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import kz.bigdata.web.config.PostgresConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceBean {

  // region Autowired fields
  @Autowired
  private PostgresConfig postgresConfig;
  // endregion

  @Bean
  public DataSource dataSource() {
    var config = new HikariConfig();
    config.setDriverClassName("org.postgresql.Driver");
    config.setJdbcUrl("jdbc:postgresql://" + postgresConfig.host() + ":" + postgresConfig.port() + "/" + postgresConfig.dbName());
    config.setUsername(postgresConfig.username());
    config.setPassword(postgresConfig.password());
    return new HikariDataSource(config);
  }

}
