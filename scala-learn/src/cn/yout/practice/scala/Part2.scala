package cn.yout.practice.scala

/**
  * scala 面向对象
  */
class Part2{

}

class Person{
  // _ 代表所有变量，即可以不赋值，但是必须带有变量类型
  var name : String = _ // var 在java中会生成getter和setter方法
  val age = 10 // val 在java中只会生成getter方法
  private[this] val gender = "male" //类私有，只能在类中使用(this，可以换成其他包名，代表只可以在指定包中使用)
}

/**
  * 构造方式示例，类参数表直接赋值，直接执行类内语句
  * 1. 主构造器直接跟在类名后面，主构造器的参数会被编译成字段
  * 2. 主构造器执行完之后会执行类中的所有语句
  * 3. 假设主构造器声明时不带var 或val，那么相当于private[this] !!
  * @param name
  * @param age
  */
class Person2(val name:String, val age:Int){
  println("This is person2, name : " + name + " age : " + age)
  var gender :String = _
  val school = "LGD"

  /**
    * 附属构造器
    * 1. 附属构造器的名称为this
    * 2. 每一个附属构造器必须调用已经存在的主构造器或者附属构造器
    * @param name
    * @param age
    * @param gender
    */
  def this(name: String, age : Int, gender: String){
    this(name,age)
    this.gender = gender
  }
}

class Student(name: String, age: Int, major: String) extends Person2(name,age){
  println("This is student class, major is " + major)

  //子类重写父类方法和字段，必须加 override
  override val school = "TX"
  override def toString = "Override toString...."
}

/**
  * 抽象类(abstract class)
  * 1. 类的一个或多个方法没有完整定义
  * 2. 声明抽象方法不需要加abstract关键字，只需要不写方法体
  * 3. 父类可以声明抽象字段(没有初始值的字段)
  * 4. 子类重写父类抽象方法不需要加override
  * 5. 子类重写父类抽象字段时不需要加override
  */
abstract class Person3{
  def speak
  val name : String
  var age : Int
}

class Student2 extends Person3 {
  def speak: Unit ={
    println("Speak some thing")
  }

  val name: String = "Jack"
  var age: Int = 29
}

/**
  * trait 特质
  * 1. 字段和行为的集合
  * 2. 混入类中
  * 3. 通过with关键字，一个类可以拓展多个特质
  * 用法：
  * 1. 当做接口
  * 2. 带有具体实现的接口
  * 3. 带有特质的对象
  * 4. 特质从左到右被构造
  */
trait Logger{
  def log(arg : String): Unit = println(arg)
}

class TraitTest extends Logger {
  def test(): Unit = log("This is a trait test!")
}

trait Logger2{
  def log(arg : String)
}

trait ConsoleLogger{
  def log(arg : String): Unit = println(arg)
}

class TraitTest2 extends ConsoleLogger{
  def test(): Unit = log("This is trait test two")
}

trait ConsoleLogger2{
  def log(arg : String): Unit ={
    println("ConsoleLogger2 :" + arg)
  }
}

trait MessageLogger extends ConsoleLogger2{
  /**
    * 此处由于继承了ConsoleLogger，而且父类的log方法不是抽象方法
    * 此处必须加<code>override</code>
    * @param arg 参数
    */
  override def log(arg : String): Unit ={
    println("MessageLogger :" + arg)
  }
}

abstract class TraitTestAbs{
  def test()
}

/**
  * 类后连续继承两个及以上各抽象类或trait从第二个开始需要用<code>with</code>
  */
class TraitTest3 extends TraitTestAbs with ConsoleLogger2{
  def test(): Unit ={
    log("trait test 3")
  }
}

class ApplyTest{
//  def apply() = "class apply"
  def apply() = println("class apply")
  def test(): Unit ={
    println("This is a apply test")
  }
}

object ApplyTest{
  def apply(): ApplyTest = new ApplyTest()

  /**
    * 创建在Object里面的方法就是静态方法,并且scala里面static不是关键字
    */
  def static: Unit ={
    println("This is a static method")
  }

  var count = 0
  def incr: Unit ={
    count = count + 1
  }
}



object Part2 extends App {
  var p = new Person //括号可以省略
  println(p.name + ":" + p.age)
  p.name = "Jack"
  println(p.name + " : " + p.age)

  var p2 = new Person2("Jack", 20)
  var p3 = new Person2("Tom", 15, "Male")
  println(p3.name + ":" + p3.age + ":" + p3.gender)

  var student = new Student("Marry", 18, "Math")
  println(student.name + ":" + student.age + ":" +student.school)

  println("抽象类示例")
  var student2 = new Student2
  println(student2.name + " : " + student2.age)
  student2.speak

  println("\ntrait，注：可以当成带有实现方法的接口")
  val traitTest = new TraitTest
  traitTest.test()

  println("\ntrait提供抽象方法")
  val traitTest2 = new TraitTest2
  traitTest2.test()

  println("\ntrait和抽象类混用")
  val traitTest3 = new TraitTest3
  traitTest3.test()
  //通过在类后加with混入次方法想要实现的log方法
  //注意：混入的类必须继承原类即ConsoleLogger2 (蛋糕模式！！)（带有特质的对象）
  val traitTest4 = new TraitTest3 with MessageLogger
  traitTest4.test()

  //apply用法
  println("\napply用法")
  //类名后加括号调用的是Object的apply方法，变量后加括号(clazzApply())调用的是class的apply方法
  val applyTest = ApplyTest()
  applyTest.test()
  val clazzApply = new ApplyTest()
  clazzApply()

  //单例对象
  println("\n单例对象")
  //因为object里面的方法为static所以可以用其特性，做单例对象
  for (i <- 1 to 10){
    ApplyTest.incr
  }
  println("count :" + ApplyTest.count)

  //包
  println("\n包(package com.youtna)")
  /**
    * 包
    * 1. 支持嵌套，下层可以访问上层作用域的名称
    * 2. 可串联
    *   package cn.yout{
    *     package scala{
    *       cn和yout中的队scala不可见，不能被scala访问到
    *     }
    *   }
    * 3. 顶部标记
    *    package cn.a
    *    package cn.b 也是分开访问与可串联类似
    * 4. 包对象
    * 5. 包可见性
    * 6. 包在任意地方都可以引入，作用域至该语句所在块的末尾
    *   {
    *     import xxx.xx.xx
    *   }
    *   在此就访问不到xx
    * 6. 重命名包引入成员(xx => yy)
    *   import java.Util.{HashMap =>JavaHashMap}
    *   之后可以调用JavaHashMap
    * 7. 隐藏方法(xx => _)
    *   HashMap =>_
    *   之后就访问不到HashMap
    * 8. 自动引入(java.lang._ scala._)
    * ...
    */

  /**
    * 模式匹配
    * 1. 标准用法，match(匹配到合适的立即返回，不需要break)
    * 2. 使用守卫
    * 3. 匹配类型
    */
  //标准用法
  println("\n模式匹配")
  val value = 2
  var result = value match {
    case 1 => "one"
    case 2 => "two"
    case _ => "other number"
  }
  println("result :" + result)
  println("模式匹配中case里面可以加if判断")
  result = value match {
    case i if i%2 == 0 => "even number"
    case i if i%2 == 1 => "odd number"
    case _ => "error"
  }
  println("result is :" + result)
  println("模式匹配可以匹配类型")
  def t(obj : Any) = obj match {
      case _ : Int => println("Int") //_代表任意变量，也可以用i/X等变量
      case _ :String => println("String")
      case _ => println("other type")
    }

  t(1.0D)
}
