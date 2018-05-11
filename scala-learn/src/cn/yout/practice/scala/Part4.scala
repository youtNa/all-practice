package cn.yout.practice.scala

import java.io.File

import scala.io.Source

class Part4 {

}

class RichFile(val f : File){
  def read = Source.fromFile(f).mkString
}

//companion object(伴生函数)
object Context{
  implicit def file2RichFile(f : File) = new RichFile(f)
  implicit val _string : String = "Hello"
  implicit class ImClass(x : Int){
    val add2 = x + 2
  }
}

object TestT{
  def print(context : String)(implicit dd : String): Unit ={
    println(dd + ":" + context)
  }
}

object Part4 extends App{
  /**
    * 隐式转换
    * 目的： 为现有类库增加功能
    *
    * 引入隐式转换
    * 位于源或者目标类型的companion object(伴生函数)中的隐式函数
    * 位于当前作用域可以以单个标识符指代的隐式函数
    *
    * 隐式转换的规则
    * 如果代码能够在不使用隐式转换的前提下通过编译，则不使用隐式转换
    * 编译器不会同时使用过个隐式转换
    * 存在二义性的隐式转换不被允许
    */
  //单个标识符指代的隐式函数
  import Context._
    new File("").read
  TestT.print("jack")("Hello1")
  /**
    * 隐式参数(带implicit标识的参数)
    */
  //Context 中把String做了隐式转换，之后所有用implicit修饰的String类型都会被附上该值
  TestT.print("jack")

  /**
    * 利用隐式参数进行隐式转换
    */
  // 隐式参数会对T类型调用order方法,如注释部分
  def smaller[T](a : T, b : T)(implicit order : T => Ordered[T]): T ={
    if (a < b) a else b
    //    if (order(a) < order(b)) a else b
  }

  smaller(1,2)
  smaller("AA","BB")

  /**
    * 隐式类 implicit
    * 必须定义在另一个class/object/trait里面
    * 构造器中只能带一个不是implicit类型的参数
    * 作用域中不能有与隐式类类名相同的成员变量，函数名及object名称
    */
  println(1.add2)

  /**
    * context bounds
    */
  //TODO 以后再补

  /**
    * view bounds
    */
}
