package streaming

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
  * @date 2019/01/21
  */
object Api {
  def main(args: Array[String]): Unit = {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      // We first log something to initialize Spark's default logging, then we override the
      // logging level.
      //      logInfo("Setting log level to [WARN] for streaming example." +
      //        " To override add a custom log4j.properties to the classpath.")
      Logger.getRootLogger.setLevel(Level.WARN)
    }
    val sparkConf = new SparkConf().setAppName("api").setMaster("local[2]")
    val rddQueue = new mutable.Queue[RDD[Int]]()
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    val lines = ssc.queueStream(rddQueue)
    val d = lines.map(_ + 1)

    d.print()
    ssc.start()


    for (i <- 1 to 30) {
      rddQueue.synchronized {
        rddQueue += ssc.sparkContext.makeRDD(1 to 1000, 10)
      }
      Thread.sleep(1000)
    }
    ssc.stop()
  }
}