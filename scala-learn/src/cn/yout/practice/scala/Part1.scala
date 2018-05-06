package cn.yout.practice.scala

import scala.collection.mutable.ArrayBuffer

/**
  * scala 基础知识
  */
object Part1 extends App {
  /**
    * 值与变量
    * 1. 值(val):赋值后不可变
    * 2. 变量(var):赋值后可以改变
    * tips. 一般不显示指定变量类型，可以通过赋值推断出来
    */
  val a : String = "This is val"
  var b : String = "This is var"
  b = "Modify var b"
  var c = "This is String"
  var d = 1
  println("a:" + a + " b:" + b + " c:" + c + " d:" + d)

  /**
    * 方法定义
    * def 方法名 (参数名 : 参数类型) : 返回类型 = {}
    * tips.
    *   1. 当返回参数为unit可以不写返回类型，如下:
    *     def 方法名 （参数名 : 参数类型）{}
    *   2. 当方法无入参时，调用时可以不加 括号 ，调用方法如下：
    *     方法名；
    *   3. 带名参数(赋值顺序可以和定义顺序不一致)
    *   4. 默认参数,如下：
    *     def 方法名 (参数名 : 参数类型 = 默认值){}
  *     5. 变长参数(int*)
    *   6. scala 没有静态方法(static),通过 object 实现
    *
    */
  def test (param : String) : String = {
    "This is a first method, param is " + param
  }
  println(test("a"))
  //Tips 1
  def noReturn (param : String){
    println("This method has no return, param is " + param)
  }
  noReturn("b")

  //Tips 2
  def noParam (): Unit ={
    println("This method has no param")
  }
  noParam //从IDEA提示来看，是不建议这样处理的

  //Tips 3
  def moreParam(param1 :String, param2 : String): Unit ={
    println("param1 is " + param1 + " param2 is " + param2)
  }
  moreParam(param2 = "12", param1 = "34")

  //Tips 4
  def defaultParam (param : String = "Jack"): Unit ={
    println("Param is " + param)
  }
  defaultParam()

  //Tips 5
  def paramTest(elems : Int*): Unit ={
    println(elems)
  }
  paramTest(1,2,3,4)


  /**
    * 条件语句(if)
    * 循环语句 (for,while ,to ,until , Range没有 break 和continue)
    */
  var i = 1
  // if
  println(if(i == 1) 2 else 3)
  // to 1 -- 10
  println(1.to(10))
  1 to 10 //效果同上
  //until 1 -- 9
  println(1.until(10))
  //Range 步长为1
  println(Range(1,10))
  //Range 步长为2
  println(Range(1,10,2))

  //for 循环
  for (t <- 1 to 10){
    print(t)
  }
  println()
  //for 与 if 结合使用
  for (t <- 1 to 10 if t %2 == 0) print(t)

  /**
    * Lazy Value 懒加载，真正使用时才会初始化
    * tips. lazy只能修饰 val
    */
  lazy val t = 1
  println(t)

  /**
    * 异常处理
    */
  try {
    val n = 0
    println(1/n)
  }catch {
    case e : Exception => System.err.println(e)
    case _ => println("Error")
  }

  /**
    * 定义数组(定长数组)
    * 1. new 实例化
    * 2. 直接赋值
    */
  val array_name = new Array[String](2)
  array_name(0) = "Jack"
  array_name(1) = "Marry"
  for (i <- array_name) print(i + " ")
  println()

  val array_name_2 = Array("12","34")
  for (i <- array_name_2) print(i + " ")
  println()

  /**
    * 定义数组(变长数组)
    */
  var array_name_3 = ArrayBuffer[Int]()
  array_name_3 += 1
  array_name_3 += 2
  array_name_3 += (3,4)
  array_name_3 ++= Array(3,4)
  array_name_3.insert(0,0)
  array_name_3.remove(5)
  for (i <- array_name_3) print(i + " ")
  println()
  array_name_3.remove(0,2) //从下标为0元素开始删除两个元素
  for (i <- array_name_3) print(i + " ")
  println()
  println(array_name_3.toArray.sum) //转为Array后求和， min求最小， max求最大
  array_name_3.trimEnd(2) //移除末尾n个元素
  for (i <- array_name_3) print(i + " ")
  println()

  /**
    * Map 操作(可变与不可变)
    */
  var old = Map("Jack" -> 20 , "Tom" -> 30)
  old += ("Merry" -> 4)
  for ((k,v) <- old) print(k + " -> " + v + " ")
  println()
  for ((k,_) <- old) print(k + " ")
  println()

  println(old.get("Jack")) //必须含有key否则报错
  println(old.getOrElse("Test",0)) //如果没有key则返回默认值
  old += ("Lucy" -> 2)
  old -= "Jack"

  /**
    * 元组
    */
  var elems = (1,2,34,5)
  println(elems._1) //元组下标从 1 开始，而不是0
  for (elem <- elems.productIterator) print(elem + " ")
  println()

}
