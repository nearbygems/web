package kz.bigdata.spark

import kz.bigdata.web.config.PostgresConfig
import kz.bigdata.web.register.SparkRegister
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.row_number
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.util.Properties

@Component
class SparkRegisterImpl extends SparkRegister {

  @Autowired val postgresConfig: PostgresConfig = null

  override def saveToSmartPhones(csv: String): Unit = {

    println(csv)

    val spark = SparkSession.builder.master("local").appName("Spark").getOrCreate

    val schema = new StructType()
      .add("title", "string")
      .add("price", "int")
      .add("seller", "string")
      .add("ram", "string")
      .add("memory", "string")

    val df = spark.read.schema(schema)
      .csv(csv)
      .toDF

    df.createOrReplaceTempView("smartphones")

    val num = Window.partitionBy("title").orderBy("price")

    df.withColumn("row_number", row_number.over(num))

    val url = "jdbc:postgresql://" + postgresConfig.host + ":" + postgresConfig.port + "/" + postgresConfig.dbName
    val properties = new Properties
    properties.put("user", postgresConfig.username)
    properties.put("password", postgresConfig.password)
    properties.put("dbname", postgresConfig.dbName)
    properties.put("jdbcDriver", postgresConfig.driver)
    properties.put("jdbcUrl", url)

    Class.forName(postgresConfig.driver)

    val sqlDF = spark.sql("SELECT title as brand, seller as model, price FROM smartphones")

    sqlDF.write.mode(SaveMode.Append)
      .jdbc(url, "smartphones", properties)
  }

}
