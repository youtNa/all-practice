package cn.yout.practice.scala

import scala.collection.immutable.SortedSet
import scala.collection.mutable.ListBuffer

class Part3 {

}

object Part3 extends App {
  /**
    * 值函数(将函数，赋值给一个变量)
    * 在赋值是 函数后需要加 <code>_</code> !!
    *
    * @param x 参数1
    * @param y 参数2
    * @return
    */
  //
  def add(x: Int, y: Int): Int = {
    x + y
  }

  var _add = add _
  println(_add(1, 2))

  /**
    * 嵌套函数
    * 在def中还可以嵌套def
    * tips. 1. 内层def 可以访问上层def中的变量。
    */
  def add2(x: Int, y: Int, z: Int): Int = {
    def add4(x: Int, y: Int): Int = {
      x + y
    }

    println("add4 : " + add4(add4(x, y), z))

    //tips. 1
    def add3(): Int = {
      x + y
    }

    println("add3 : " + add3())
    add3()
  }

  add2(1, 2, 3)

  /**
    * 匿名函数
    * tips. 1. 可以把匿名函数赋值给变量，以进行调用
    * 2. 一种常见用法是作用在list上，对list中所有值进行处理
    */
  (x: Int) => x + 2

  //tip1
  var fun = (x: Int) => x + 3
  println(fun(10))
  //tip2
  var list = List(1, 2, 3, 4)
  var list2 = list.map((x: Int) => x * 2)
  println(list2)

  /**
    * 函数作为参数
    * 因为在scala中函数为一等公民(与参数同级别)所以可以把函数作为参数
    * <code>fun(函数)</code>
    * <code>def fun(f : (Int,Int) => Int):Int</code>
    * tips. 1.上一个例子为传函数返回值，这里的例子是传值返回函数
    */
  def fun(f: (Int, Int) => Int): Int = {
    f(3, 4)
  }

  println(fun((x: Int, y: Int) => x + y))

  //tip 1
  def mulBy(faction: Double) = {
    (x: Double) => x * faction
  }

  println(mulBy(20)(2))

  /**
    * 闭包
    * 闭包 = 代码 + 用到的非局部变量
    * 以下示例<code>b</code>为非局部变量，与fun2中的代码混合这就是闭包
    * 当非局部变量b发生变化时，fun2对应的返回值也会变化
    */
  var b = 10
  val fun2 = (x: Int) => x + b
  println(fun2(2))
  b = 12
  println(fun2(2))

  /**
    * 参数简化与类型推导
    * 1. list.map((x : Int) => x * 2) 最原始的方式
    * 2. list.map((x) => x * 2) 由于list的泛型时确定的所以x的类型可以省略
    * 3. list.map(x => x * 2) 如果只有一个参数的情况先，可以省略括号
    * 4. list.map(_ * 2) 本身只有一个参数，并且参数是确定的，可以用下划线代替 x
    */
  val list3 = List(2, 3, 4, 5)
  println(list3.map((x: Int) => x * 2))
  println(list3.map((x) => x * 2))
  println(list3.map(x => x * 2))
  println(list3.map(_ * 2))

  /**
    * partial function 偏函数
    * def add(x : Int, y : Int, z : Int)
    * def _add = add(_ : Int , _ : Int, 0)
    */
  def add3(x: Int, y: Int, z: Int): Int = x + y + z
  val _add3 = add3(_: Int, _: Int, 2)
  println(_add3(1,2))

  /**
    * Currying
    * def add(x : Int)(y : Int) = x + y
    * add(1)_
    */
  def add4(x : Int)(y : Int) = x + y
  val _add4 = add4(2)_
  println(_add4(5))

  /**
    * 重要高阶函数
    * map
    * filter
    * reduce/reduceLeft/reduceRight
    * fold/foldLeft/foldRight
    */
  val list4 = List(1,2,3,4,5)
  println(list4.map(_ * 2)) //map
  println(list4.filter(_ > 4)) //filter
  println(list4.reduce((x : Int, y : Int) => x + y)) //reduce
  println(list4.reduce(_ + _)) //reduce 对上面的写法简化
  println(list4.reduceLeft(_ - _)) //reduceLeft 从左向右处理
  println(list4.reduceRight(_ - _)) //reduceRight 从右向左处理
  println(list4.fold(10)(_ - _)) //fold 提供第一次运算初始值
  println(list4.foldLeft(10)(_ - _))
  println(list4.foldRight(10)(_ - _))
  // foldRight 处理步骤如下
  //                5 - 10
  //            4 -   -5
  //         3 -   9
  //      2 -  -6
  //  1 -   8
  // 7

  /**
    * By-name parameters
    */
  //By-value parameters
  def test(flag : Boolean): Unit ={
    //此处flag为值 true
    println(flag)
  }
  test(3 > 2)
  //By-name parameters 类似懒加载
  def test2(flag : => Boolean) : Unit = {
    //此处flag为方法 3 > 2
    println(flag)
  }
  test2(3 > 2)

  /**
    * 集合
    *   seq
    *   set
    *   map
    * scala 的集合操作和spark的集合操作是平行的！！！
    *
    * 可变和不可变集合
    *   scala.collection.mutable.Map 可变Map
    *   scala.collection.immutable.Map 不可变Map
    *   scala.collection.mutable.List 可变List
    *   scala.collection.immutable.List 不可变List
    *   immutable 已经内置到scala中，调用时不需要import；mutable在使用时需要import
    */
  val map = Map(1 -> 2, 3 -> 4) //mutable
  val map_mu =  scala.collection.mutable.Map(1 ->2, 3 -> 4)
  println(map)
  println(map_mu)

  /**
    * Range
    * 1. Range(start : Int, end : Int ,step : Int)
    * 2. 提供apply方法
    * 3. step可为正或者负，但不能为0
    * 4. 左闭右开
    */
  println(Range(1,10,2))
  println(Range(10,1,-1))

  /**
    * List
    * 1. List由Nil或者head + tail构成，其中tail有事一个List
    * 2. new-value :: list
    * 3. 构建：List(1,2,3) 或者 1 :: 2 :: 3 ::Nil
    * 4. 基于List的模式匹配
    * 5. 利用迭代或者递归处理List
    */
  val l = List(1,2,3,4)
  println("list heal:" + l.head)
  println("list tail:" + l.tail)
  println("new-value :: list:" + 1 :: l)
  println("构建：" + List(1,2) + "或者" + 1 :: 2 :: 3 :: Nil)
  println("基于List的模式匹配")
  def listMatch(list: Any) = list match {
    case 0 :: Nil => println("list 0")
    case x :: y :: Nil => println("list first:" + x + " second:" + y)
    case 0 :: tail => println("list start 0")
    case _ => println("other")
  }
  listMatch(1,2,3)
  listMatch(List(0))
  listMatch(List(1,2))
  listMatch(List(0,1,2,3))
  listMatch(List(1,2,3))

  /**
    * ListBuffer(可变)
    * 1. +=/ ++=/-=/--=
    * 2. 转为不可变 toList/toArray
    *
    */
  val lb = ListBuffer(1)
  lb += 1
  println(lb)
  lb ++= 2 ::3 :: 4 :: Nil
  println(lb)
  lb -= 1
  println(lb)
  lb --= 3 :: 2 :: Nil
  println(lb)
  println(lb.toList)
  println(lb.toArray)

  /**
    * immutable Set(不可变Set)
    * 1. +/++
    * 2. -/--
    * 3. scala.collection.immutable.SortedSet
    */
  var set = Set(1,2,3,4)
  println(set + 5)
  println(set ++ List(8,4,6))
  println(set - 1)
  println(set -- List(1,2,5))
  println(SortedSet(4,2,5,6,2))

  /**
    * mutable Set(可变Set)
    * 1. +/++/-/--都会创造一个新的Set
    * 2. +=/++=/-=/--= 不会床罩新的Set
    * 3. scala.collection.mutable.SortedSet(trait)
    * 4. TreeSet&BitSet
    */

  /**
    * 重要方法
    * 1. map
    * 2. foreach/take
    * 3. filter
    * 4. flatten/flatMap
    * 5. reduce/fold reduceLeft/foldLeft or Right
    * 6. sum/max/min/count
    * 7. zip
    */
  val l2 = List(1,2,3,4)
  println(l2.map(_ * 2))
  l2.foreach(println(_))
  println(l2.take(3))
  println(l2.filter(_ > 2))
  val l3 = List(List(1,2),List(3,4))
  println(l3.flatten)
  println(l3.flatMap(_.map(_ * 2)))
  println(l2.reduce(_ + _))
  println(l2.sum)
  println(l2.max)
  println(l2.min)
  println(l2.count(_ > 2))
  val l4 = List(1,2,3,4,5)
  val l5 = List("A","B","C","D")
  println(l4 zip l5)

}


