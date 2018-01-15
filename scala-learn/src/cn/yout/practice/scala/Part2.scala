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


}
