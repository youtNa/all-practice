package wordCount

import org.apache.spark.{SparkConf, SparkContext}

object Main {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("My App").setMaster("local");
    val sc = new SparkContext(conf)
    val lines = sc.textFile("README.md")
    val words = lines.flatMap(_.split(" "))
    val counts = words.map((_,1)).reduceByKey(_ + _)
    for (one <- counts.take(3)) //抽取10个元素
      println(one)
    println("======== 取所有数据 ========")
    for (one <- counts.collect())
      println(one)

  }

}
