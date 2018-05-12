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

class A[T](a : T){
  println(a)
  def pr(d : T) = println(d)
}

trait B{
  def pr[T](a : T) = println(a)
}

class C() extends B{

}

//设置上界，T必须是继承了Comparable类的
class Bounds[T <: Comparable[T]](first : T, second : T){
  def smaller = if (first.compareTo(second) < 0) first else second
}
//view bounds 设置上界，其中T可以隐式转换为Comparable即可
class Bounds3[T <% Comparable[T]](first : T, second : T){
  def smaller = if (first.compareTo(second) < 0) first else second
}
//context bounds 通过隐式转换的方式实现上界的设置，等同Bounds3
class Bounds4[T](first : T, second : T)(implicit en : T => Comparable[T]){
  def smaller = if (first.compareTo(second) < 0) first else second
}

//设置下界，R必须是T的超类
class Bounds2[T](first : T, second : T){
  def compare[R >:T](newFirst : R) = new Bounds2(newFirst,second);
}

class Person_bound(val name :String)

class Student_bound(name: String) extends Person_bound(name)

class X[+T](x : T)
class X2[-T](x : T)

object Part4 extends App{
  /**
    * 泛型类/泛型函数
    * 注意：
    * 1. Object不能泛型,trait可以泛型
    *
    * Lower bounds(下界)
    * Upper bounds(上界)
    * View bounds T<%v -------> T到V的隐式转换
    * Context bounds [T:M] ------> M[T]
    */
  new A[Int](1)
  new A(1) //scala 会从变量中识别类型，所以[Int】可以不写
  //new A[String](1) //如果写出泛型scala就会做参数类型校验
  val d = new C()
  d.pr(1)

  val smaller = new Bounds("21","22")
  println(smaller.smaller)

  val smaller2 = new Bounds3(1,2)
  println(smaller2.smaller)

  val smaller3 = new Bounds4(1,2)
  println(smaller3.smaller)

  val s1 = new Student_bound("A")
  val s2 = new Student_bound("B")
  val bound = new Bounds2(s1,s2)
  println(bound)
  var p = new Person_bound("C")
  println(bound.compare(p))

  /**
    * 类型约束
    * T =:= U 判断类型T是不是等于U
    * T <:< U 判断类型T是不是U的子类型
    * T <%< U 判断类型T是不是能隐式转换到U
    */

  /**
    * 型变
    * 协变 covariant + 用作返回类型
    * 逆变 contravariant - 用来做参数类型
    */
  //协变，泛型前面有+，如果s1是p的子类则 a是b的子类
  var a = new X(s1)
  var b = new X(p)
  //逆变,泛型前面有-，如果s1是p的子类则 b是a的子类
  a = new X(s1)
  b = new X(p)

  /**
    * 类型通配符
    * java 为？
    * scala 为 _
    */
  //java <? extends AA>
  //scala [_ <: AA]

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
  try{
    new File("").read
  } catch{
    case e:Exception => println(e)
  }
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
