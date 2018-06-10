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
    printOutPutArray(mapRDD)

    /**
      * filter test 过滤
      */
    val filterRDD = text.filter(_.contains("#")).collect()
    println("filter 接收一个函数，并将RDD中满足这个函数的元素放入新的RDD中返回")
    printOutPutArray(filterRDD)

    /**
      * flatMap test
      */
    val flatMapRDD = text.flatMap(_.split(" ")).collect()
    val mapRDD2 = text.map(_.split(" ")).collect()
    println("flatMap 接收一个函数，把这个函数作用到RDD的每个元素中，并且返回有序的迭代器。\r\n" +
      "输出一个包含各个迭代器可访问的所有元素的RDD。")
    printOutPutArray(flatMapRDD)
    println("flatMap 和map的区别，我们可以flatMap看做把map返回值的迭代器\"拍扁\"")
    printOutPutArray(mapRDD2)

    /**
      * distinct test
      */
    val distinctRDD = text.distinct().collect()
    println("对RDD元素去重")
    printOutPutArray(distinctRDD)

    /**
      * union test
      */
    val text2 = sc.textFile("RDDFILE")
    val unionRDD = text.union(text2).collect()
    println("生成一个包含两个RDD中所有元素的RDD")
    printOutPutArray(unionRDD)

    /**
      * intersection test
      */
    val intersectionRDD = text.intersection(text2).collect()
    println("只返回两个RDD都有的元素")
    printOutPutArray(intersectionRDD)

    /**
      * subtract test
      */
    val subtractRDD = text.subtract(text2).collect()
    println("subtract 接收另一个RDD作为参数，返回一个有只存在第一个RDD中而不存在第二个RDD中的所有元素组成的RDD")
    printOutPutArray(subtractRDD)

    /**
      * cartesian test
      */
    val cartesianRDD = text.cartesian(text2).collect()
    println("笛卡尔积")
    printOutPutArray(cartesianRDD)

    /**
      * reduce test
      */
    val reduceRDD = text.reduce(_ + " | " + _)
    println("reduce 接收一个函数作为参数，这个函数操作RDD中的元素类型的两个数据并返回一个同样类型的新元素。")
    printOutPut(reduceRDD)

    /**
      * fold test
      */
    val foldRDD = text.fold("|")(_ + " | " +  _)
    println("fold 与reduce类似，会有一个初始值。每个分区计算都会加上这个初始值")
    printOutPut(foldRDD)

    /**
      * aggregate test
      */
    val aggregateRDD = text.aggregate(("","1"))((x,y) => (x._1 + " | " + y, x._2 + " | 1"),(x1,x2) => (x1._1 + x2._1,x1._2 + " | " + x2._2))
    println("agregate 第一个参数为分区的初始值，第二个参数对每一分区进行聚合操作，第三个参数把分区中的聚合结果进行聚合")
    printOutPut(aggregateRDD)

    /**
      * collect test
      */
    val collectRDD = text.collect()
    println("collect 取出RDD所有的元素，要求RDD的所有元素能够存储在一台机器内存中。所以一般在单测时使用。")
    printOutPutArray(collectRDD)

    /**
      * take test
      */
    val takeRDD = text.take(2)
    println("take 返回RDD中n个元素，并且尝试访问尽量少的分区，因此会得到不均衡的集合")
    printOutPutArray(takeRDD)

    /**
      * top test
      */
    val topRDD = text.top(2)
    println("top 如果数据定义了顺序，则取出RDD的前n个元素。")
    printOutPutArray(topRDD)

    /**
      * foreach test
      */
    println("\nforeach 对每个元素做操作，不用吧所有RDD元素都发送到单台机器上处理")
    printOutPut("")
    text.foreach(x => println("foreach : " + x))
  }

  def printOutPut[T](rDD : T): Unit ={
    println("======== 输出示例 =======")
    println(rDD)
  }

  def printOutPutArray[T](rDDs: Array[T]): Unit = {
    println("======== 输出示例 =======")
    for (one <- rDDs) println(one)
  }
}
