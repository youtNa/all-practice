package streaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
  * @date 2019/01/21
  */
object Api {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("api").setMaster("local[2]")
    val rddQueue = new mutable.Queue[RDD[Int]]()

    val ssc = new StreamingContext(sparkConf, Seconds(2))
    val lines = ssc.queueStream(rddQueue)
    val d = lines.map(_ + 1).filter(_ > 4)
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