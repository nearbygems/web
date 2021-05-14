package kz.bigdata.web.beans;

import kz.bigdata.web.dao.BeanConfigDao;
import liquibase.integration.spring.SpringLiquibase;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackageClasses = BeanConfigDao.class)
public class DbConfiguration {

  @Autowired
  private DataSource dataSource;

  @Bean
  public SpringLiquibase liquibase() {
    var liquibase = new SpringLiquibase();
    liquibase.setChangeLog("classpath:/liquibase/changelog-master.xml");
    liquibase.setDataSource(dataSource);
    return liquibase;
  }

}

