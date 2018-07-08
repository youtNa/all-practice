package advance

import org.apache.spark.{SparkConf, SparkContext}


object Handle {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("advance_handle")
    val sc = new SparkContext(conf)

    val file = sc.textFile("RDDFILE")

    /**
      * 累加器
      * 工作节点上的任务不能拿访问累加器的值。
      * 只有驱动器才可以
      */
    val lines = sc.longAccumulator
    val array = sc.collectionAccumulator[String]

    val callSigns = file.map(line => {
      if (line.contains("INFO")) {
        lines.add(1)
        array.add("info")
      }
      line.split(" ")
    })

    callSigns.collect()
    println("accumulator 必须在执行RDD行动操作后获取累计器的值。")
    println("===== 输出结果 =====")
    println(lines.value)
    println(array.value)

    /**
      * mapPartitions test
      */
    println("mapPartitions 对每个分区进行map操作")

    val mapPartitionsRDD = file.mapPartitions(mapPartitions).collect()
    for (t <- mapPartitionsRDD){
      println(t)
    }

    println(mapPartitionsRDD)

  }

  def mapPartitions(lines : Iterator[String]) : Iterator[(String,Int)] = {
    var ret = List[(String,Int)]()
    while (lines.hasNext){
      val t = lines.next()
      println(t)
      ret.::= (t,t.length)
    }
    ret.iterator
  }
}
