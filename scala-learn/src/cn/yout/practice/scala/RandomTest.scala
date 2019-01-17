package cn.yout.practice.scala

import java.util.Random

object RandomTest extends App {
  var arr1 = new Array[(Int, Array[Byte])](10)
  val random = new Random
  for (i <- 0 until 10){
    val byteArr = new Array[Byte](10)
    random.nextBytes(byteArr)
    arr1(i) = (random.nextInt(Int.MaxValue), byteArr)
  }

//  for{
//    println("random = ${random}")
//  }
    test

    def test: Unit ={

    }
  println(arr1)

  var d = new(test)
  d.max
}


class test{
  var t = 1;
  val d :String = {
    if (t == 1){
      "st"
    } else {
      "tt"
    }
  }

  def max: Unit ={
    println(d)

    println(d)
    t = t +1
  }
}
