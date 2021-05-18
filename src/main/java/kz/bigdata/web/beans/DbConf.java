package kz.bigdata.web.beans;

import kz.bigdata.web.dao.BeanConfDao;
import liquibase.integration.spring.SpringLiquibase;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackageClasses = BeanConfDao.class)
public class DbConf {

  // region Autowired fields
  @Autowired
  private DataSource dataSource;
  // endregion

  @Bean
  public SpringLiquibase liquibase() {
    var liquibase = new SpringLiquibase();
    liquibase.setChangeLog("classpath:/liquibase/changelog-master.xml");
    liquibase.setDataSource(dataSource);
    return liquibase;
  }

}

