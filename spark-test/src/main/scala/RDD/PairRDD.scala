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
      * sortByKey test
      */
    val sortByKey = pairs.sortByKey().collect()
    println("sortByKey 返回一个根据键排序的RDD")
    printOutPutArray(sortByKey)

    val others = sc.parallelize(Seq((3,9)))

    /**
      * subtractByKey test
      */
    val subtractByKeyRDD = pairs.subtractByKey(others).collect()
    println("subtractByKey 删掉RDD中与other RDD中key相同的元素")
    printOutPutArray(subtractByKeyRDD)

    /**
      * join test
      */
    val joinRDD = pairs.join(others).collect()
    println("join RDD 内连接")
    printOutPutArray(joinRDD)

    /**
      * rightOuterJoin test
      */
    val rightOuterJoinRDD = pairs.rightOuterJoin(others).collect()
    println("rightOuterJoin 右外链接")
    printOutPutArray(rightOuterJoinRDD)

    /**
      * leftOuterJoin test
      */
    val leftOuterJoinRDD = pairs.leftOuterJoin(others).collect()
    println("leftOuterJoin 左外链接")
    printOutPutArray(leftOuterJoinRDD)

    /**
      * combineByKey test
      */
    val combineByKeyRDD = pairs.combineByKey(
      (v) => (v, 1),
      (acc: (Int, Int), v) => (acc._1 + v, acc._2 + 1),
      (acc1: (Int, Int), acc2: (Int, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2)
    ).collect()
    println("combineByKey 第一个参数：当前分区key第一此出现value调用此方法，第二个参数：当前分区非第一此出现merge value，第三个参数：合并分区值。")
    printOutPutArray(combineByKeyRDD)

    /**
      * sortByKey test
      */
    val sortByKeyRDD = pairs.sortByKey(false).collect()
    println("sortByKey 利用key对RDD排序,默认为升序true, false为降序")
    printOutPutArray(sortByKeyRDD)

    /**
      * countByKey test
      */
    val countByKeyRDD = pairs.countByKey()
    println("countByKey 计算RDD中key出现了几次")
    printOutPut()
    println(countByKeyRDD)

    /**
      * collectAsMap test
      */
    val collectAsMapRDD = pairs.collectAsMap()
    println("collectAsMap 结果一映射表的形式返回，并且若有相同key，后面的会覆盖前面的")
    printOutPut()
    println(collectAsMapRDD)

    /**
      * lookup test
      */
    val lookupRDD = pairs.lookup(3)
    println("lookup 返回给定键对应的所有值")
    printOutPut()
    println(lookupRDD)
  }

  def printOutPutArray[T](rDDs: Array[T]): Unit = {
    printOutPut()
    for (one <- rDDs) println(one)
  }

  def printOutPut() : Unit = println("======== 输出示例 =======")
}
