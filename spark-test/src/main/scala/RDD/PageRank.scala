package RDD

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object PageRank {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("page_rank")
    val sc = new SparkContext(conf)


    val links = sc.parallelize(Seq(("page1",Seq("page2","page3","page4")),("page2",Seq("page1","page5","page3","page6")),("page3",Seq("page1","page2","page4"))))
                  .partitionBy(new HashPartitioner(5)).persist()
    // 将每个页面的排序值初始化为1.0，由于使用mapValues，生成RDD
    // 的分区方式会和“links”的一样
    var ranks = links.mapValues(_ => 1.0)

    // 运行10轮的PageRank迭代
    for (i <- 0 until 10){
      val contribution = links.join(ranks).flatMap{
        case (_,(links,rank)) =>
          links.map(dest => (dest, rank/links.size))
      }
      printOutPutArray(contribution.collect())
      ranks = contribution.reduceByKey(_ + _).mapValues(0.15 + 0.85 * _)
      printOutPutArray(ranks.collect())
    }

    //打印最终排名
    printOutPutArray(ranks.collect())
  }

  def printOutPutArray[T](rDDs: Array[T]): Unit = {
    println("======== 输出示例 =======")
    for (one <- rDDs) println(one)
  }
}
