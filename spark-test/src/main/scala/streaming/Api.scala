package streaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * @date 2019/01/21
  */
object Api {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("api").setMaster("local[2]")
    val rddQueue = new mutable.Queue[RDD[Int]]()

    val ssc = new StreamingContext(sparkConf, Seconds(2))
    val lines = ssc.queueStream(rddQueue)
    // map & filter example
    val mapAndFilter = lines.map(_ + 1).filter(_ > 1)
    // flatMap & mapPartitions
    val d = mapAndFilter.flatMap(List(_, 20, 30, 40)).mapPartitions(iteratorAdd)
    // transform
    val t = d.transform(rdd => {
      println("=====" + rdd.id)
      rdd
    })
    t.print()


    ssc.start()

    for (i <- 1 to 30) {
      rddQueue.synchronized {
        rddQueue += ssc.sparkContext.makeRDD(1 to 1000, 10)
      }
      Thread.sleep(1000)
    }
    ssc.stop()
  }

  def iteratorAdd(input: Iterator[Int]) : Iterator[String] = {
    val output = ListBuffer[String]()
    for (t <- input){
      output += t.toString + " map"
    }
    output.iterator
  }

}