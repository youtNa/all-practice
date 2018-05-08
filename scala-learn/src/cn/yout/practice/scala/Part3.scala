package cn.yout.practice.scala

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
}
