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
  * 1. 主构造器直接跟在类名后面，最构造器的参数会被编译成字段
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
  //注意：混入的类必须继承原类即ConsoleLogger2 (蛋糕模式！！)
  val traitTest4 = new TraitTest3 with MessageLogger
  traitTest4.test()

}
