package kz.bigdata.spark

import kz.bigdata.web.config.{HdfsConf, PostgresConf, SparkConf}
import kz.bigdata.web.register.SparkRegister
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SaveMode, SparkSession}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.io.File

@Component
class SparkRegisterImpl extends SparkRegister {

  // region Autowired fields
  @Autowired val postgresConfig: PostgresConf = null
  @Autowired val sparkConfig: SparkConf = null
  @Autowired val hdfsConfig: HdfsConf = null
  // endregion

  def getBrandAndModel(row: Row): Row = {
    val title = row.getString(0).trim.split("\\s+")
    if (title.nonEmpty) {
      val brand = title(0)
      val model = StringBuilder.newBuilder
      for (i <- 1 until title.length) {
        if (!title(i).toLowerCase().contains("gb")) {
          model ++= title(i) + " "
        }
      }
      return Row.fromSeq(Seq(brand.trim, model.mkString.trim, row.getString(1).trim.toInt))
    }
    Row.fromSeq(Seq(null, null, null))
  }

  override def saveToSmartPhones(csv: String): Unit = {

    val file = new File(csv)

    val spark = SparkSession.builder
      .master("spark://" + sparkConfig.host + ":" + sparkConfig.port)
      .appName("Web-App")
      .getOrCreate

    val df = spark.read
      .format("csv")
      .option("sep", ";")
      .option("header", "true")
      .load(csv)

    val rows = df.select("title", "price")
      .collect
      .toStream
      .map(row => getBrandAndModel(row))
      .filter(row => !row.anyNull)

    val schema = StructType(Array(
      StructField("brand", StringType),
      StructField("model", StringType),
      StructField("price", IntegerType)))

    val smartphones = spark.createDataFrame(spark.sparkContext.parallelize(rows), schema)

    smartphones.createOrReplaceTempView("smartphones")

    val result = spark.sql("select brand, model, avg(price) as price from smartphones group by brand, model")

    val url = "jdbc:postgresql://" +
      postgresConfig.host + ":" +
      postgresConfig.port + "/" +
      postgresConfig.name

    result.write
      .mode(SaveMode.Append)
      .format("jdbc")
      .option("user", postgresConfig.username)
      .option("password", postgresConfig.password)
      .option("dbname", postgresConfig.name)
      .option("dbtable", "smartphones")
      .option("driver", postgresConfig.driver)
      .option("url", url)
      .save()

    result.write
      .parquet("hdfs://" + hdfsConfig.host + ":" + hdfsConfig.port +
        hdfsConfig.folder() + file.getName.replace(".csv", ".parquet"))
  }

}
