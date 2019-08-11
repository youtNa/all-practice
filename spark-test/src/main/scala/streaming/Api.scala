package streaming

import org.apache.spark.{SparkConf, rdd}
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
    // consume from rddQueue
    val lines = ssc.queueStream(rddQueue)
    // map & filter example
    val mapAndFilter = lines.map(_ + 1).filter(_ > 1)
    // flatMap & mapPartitions
    val flatMapAndMapPartitions = mapAndFilter.flatMap(List(_, 20, 30, 40)).mapPartitions(iteratorAdd)
    // transform
    val transform1 = flatMapAndMapPartitions.transform(rdd => {
      println("transform1: id: " + rdd.id)
      rdd
    })
    val transform2 = transform1.transform((rdd, time) => {
      println("transform2: id: " + rdd.id + " time: " + time)
      rdd
    })
    transform2.print()

    // foreachRDD
    transform1.foreachRDD(rdd => {
      val values = rdd.take(10)
      for (value <- values) println("foreachRDD == " + value)
    })

    // glom
    lines.glom().map(x => for(i <- x) println("glom ==> " + i)).print()

    // repartition
    val re = lines.repartition(2).map(_ + 1)
    re.print()

    // window
    lines.window(Seconds(8),Seconds(4)).print()

    ssc.start()

    // produce to rddQueue
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