package RDD

import org.apache.spark.{SparkConf, SparkContext}


object PairRDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("PairRdd")
    val sc = new SparkContext(conf)
    val pairs = sc.parallelize(Seq((3,4),(1,2),(3,6)))

    /**
      * reduceByKey test
      */
    val reduceByKeyRDD = pairs.reduceByKey(_ + _).collect()
    println("reduceByKey 合并具有相同键的值")
    printOutPutArray(reduceByKeyRDD)

    /**
      * groupByKey test
      */
    val groupByKeyRDD = pairs.groupByKey().collect()
    println("groupByKey 对具有相同键的数据进行分组")
    printOutPutArray(groupByKeyRDD)

    /**
      * mapValues test
      */
    val mapValuesRDD = pairs.mapValues(_ + 1).collect()
    println("mapValues 对RDD中的每个值应用一个函数而不改变键")
    printOutPutArray(mapValuesRDD)

    /**
      * flatMapValues test
      */
    val flatMapValuesRDD = pairs.flatMapValues(_ to 5).collect()
    println("flatMapValues 对RDD每个值应用一个返回迭代器的函数，然后对返回的每个元素都生成一个对应原件的键值对记录。通常用于符号化")
    printOutPutArray(flatMapValuesRDD)

    /**
      * keys test
      */
    val keysRDD = pairs.keys.collect()
    println("keys 返回一个包含所有键的RDD")
    printOutPutArray(keysRDD)

    /**
      * values test
      */
    val valuesRDD = pairs.values.collect()
    println("values 返回一个包含所有值得RDD")
    printOutPutArray(valuesRDD)

    /**
      * sortByKey
      */
    val sortByKey = pairs.sortByKey().collect()
    println("sortByKey 返回一个根据键排序的RDD")
    printOutPutArray(sortByKey)
  }

  def printOutPutArray[T](rDDs: Array[T]): Unit = {
    println("======== 输出示例 =======")
    for (one <- rDDs) println(one)
  }
}
