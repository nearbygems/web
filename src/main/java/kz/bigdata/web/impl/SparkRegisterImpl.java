package kz.bigdata.web.impl;

import kz.bigdata.web.config.PostgresConfig;
import kz.bigdata.web.register.SparkRegister;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SparkRegisterImpl implements SparkRegister {

  @Autowired
  private PostgresConfig postgresConfig;

  @Override
  public void saveToSmartPhones(String csv) {

    System.out.println(csv);

    var spark = SparkSession.builder().master("spark://spark-master:7077").appName("Spark").getOrCreate();

    var schema = new StructType()
      .add("title", "string")
      .add("price", "int")
      .add("seller", "string")
      .add("ram", "string")
      .add("memory", "string");

    var df = spark.read().schema(schema)
      .csv(csv)
      .toDF();

    df.createOrReplaceTempView("smartphones");

    var url = "jdbc:postgresql://" + postgresConfig.host() + ":" + postgresConfig.port() + "/" + postgresConfig.dbName();
    var properties = new Properties();
    properties.put("user", postgresConfig.username());
    properties.put("password", postgresConfig.password());
    properties.put("dbname", postgresConfig.dbName());
    properties.put("jdbcDriver", postgresConfig.driver());
    properties.put("jdbcUrl", url);

    var sqlDF = spark.sql("SELECT title as brand, seller as model, price FROM smartphones");

    sqlDF.write().mode(SaveMode.Append).jdbc(url, "smartphones", properties);

  }
}
