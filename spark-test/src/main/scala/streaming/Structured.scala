package streaming

import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.types.{BinaryType, IntegerType, IntegralType, LongType, StringType, StructField, StructType}

import scala.util.parsing.json.JSON

object Structured {
  def main(args: Array[String]): Unit = {
//    if (args.length < 2) {
//      System.err.println("Usage: StructuredNetworkWordCount <hostname> <port>")
//      System.exit(1)
//    }

//    val host = args(0)
//    val port = args(1).toInt

    val spark = SparkSession
      .builder
      .master("local[2]")
      .appName("StructuredNetworkWordCount")
      .config("spark.sql.shuffle.partitions", 1)
      .getOrCreate()

    import spark.implicits._

    val testSchema = new StructType()
      .add("id", IntegerType)
      .add("type", StringType)
    // Create DataFrame representing the stream of input lines from connection to host:port
//    val lines = spark.readStream
//      .format("socket")
//      .option("host", host)
//      .option("port", port)
////      .format("json")
//      .schema(testSchema)
//      .load()
//    Dataset<Row> tboxDataSet = rawDataset
//      .where("topic = my_topic")
//      .select(functions.from_json(functions.col("value").cast("string"), tboxScheme).alias("parsed_value"))
//      .select("parsed_value.columnA",
//        "parsed_value.columnB",
//        "parsed_value.columnC",
//        "timestamp");
    val lines = spark.readStream
        .format("kafka")
        .option("kafka.bootstrap.servers", "192.168.1.147:9092,192.168.1.148:9092,192.168.1.149:9092")
        .option("subscribe", "topic1")
        .option("group.id","test")
        .option("maxOffsetsPerTrigger", 10)
        .load()

    lines.select(functions.from_json(functions.col("value").cast("string"), testSchema).alias("value"))
      .createOrReplaceTempView("test")

    val wordCounts = spark.sql("select value.id as id,value.type as type from test")
//    lines.selectExpr("CAST(value AS STRING)").createOrReplaceTempView("test")
//    val t = spark.sql("select * from test")
//    t.printSchema()
//
//    lines.map(row => {
//
//    })
//    lines.withColumn("jsonData",from_json(col("value"),testSchema))
//    lines.printSchema()

//    lines.createOrReplaceTempView("test")


//    val wordCounts = spark.sql("select a.id as id,a.type as type from ( select from_json(value, 'id INT, type string') as a from test)")
    wordCounts.printSchema()
//    spark.
    // Split the lines into words
//    val words = lines.as[String].flatMap(_.split(" ")).createOrReplaceTempView("test")
//
//    // Generate running word count
//    val wordCounts = spark.sql("select value,count(*) from test group by value")

    // Start running the query that prints the running counts to the console
    val query = wordCounts.writeStream
      .outputMode("update")
      .format("console")
      .start()

    query.awaitTermination()
  }

}
