package RDD

import org.apache.spark.{SparkConf, SparkContext}

object Handle {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("RDDHandle").setMaster("local")
    val sc = new SparkContext(conf)
    val text = sc.textFile("README.md")
    /**
      * map test
      */
    val mapRDD = text.map(_ + "| this is map end").collect()
    println("map 接收一个函数，把这个函数作用与RDD中每个元素，将返回值作为RDD的对应元素的值")
    printOutPut(mapRDD)

    /**
      * filter test 过滤
      */
    val filterRDD = text.filter(_.contains("#")).collect()
    println("filter 接收一个函数，并将RDD中满足这个函数的元素放入新的RDD中返回")
    printOutPut(filterRDD)

    /**
      * flatMap test
      */
    val flatMapRDD = text.flatMap(_.split(" ")).collect()
    val mapRDD2 = text.map(_.split(" ")).collect()
    println("flatMap 接收一个函数，把这个函数作用到RDD的每个元素中，并且返回有序的迭代器。\r\n" +
      "输出一个包含各个迭代器可访问的所有元素的RDD。")
    printOutPut(flatMapRDD)
    println("flatMap 和map的区别，我们可以flatMap看做把map返回值的迭代器\"拍扁\"")
    printOutPut(mapRDD2)

    /**
      * distinct test
      */
    val distinctRDD = text.distinct().collect()
    println("对RDD元素去重")
    printOutPut(distinctRDD)

    /**
      * union test
      */
    val text2 = sc.textFile("RDDFILE")
    val unionRDD = text.union(text2).collect()
    println("生成一个包含两个RDD中所有元素的RDD")
    printOutPut(unionRDD)

    /**
      * intersection test
      */
    val intersectionRDD = text.intersection(text2).collect()
    println("只返回两个RDD都有的元素")
    printOutPut(intersectionRDD)

    /**
      * subtract test
      */
    val subtractRDD = text.subtract(text2).collect()
    println("subtract 接收另一个RDD作为参数，返回一个有只存在第一个RDD中而不存在第二个RDD中的所有元素组成的RDD")
    printOutPut(subtractRDD)

    /**
      * cartesian test
      */
    val cartesianRDD = text.cartesian(text2).collect()
    println("笛卡尔积")
    printOutPut(cartesianRDD)
  }

  def printOutPut[T](rDDs: Array[T]): Unit = {
    println("======== 输出示例 =======")
    for (one <- rDDs) println(one)
  }
}
