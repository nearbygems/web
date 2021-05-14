package kz.bigdata.web.beans;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import kz.bigdata.web.config.DbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceBean {

  @Autowired
  private DbConfig dbConfig;

  @Bean
  public DataSource dataSource() {
    var config = new HikariConfig();
    config.setDriverClassName("org.postgresql.Driver");
    config.setJdbcUrl("jdbc:postgresql://" + dbConfig.host() + ":" + dbConfig.port() + "/" + dbConfig.dbName());
    config.setUsername(dbConfig.username());
    config.setPassword(dbConfig.password());
    return new HikariDataSource(config);
  }

}
