package cn.yout.practice.scala

object Test {
  def add(x:Int, y:Int):Int ={
    x + y
  }

  def add(): Unit ={
    println("Hello Word")
  }

  def sayName(name:String = "Jack"): Unit ={
    println("Hello " + name)
  }

  def main(args: Array[String]) {
    println(add(1,3))
    add()

    var printTo:String = "";
    for (i:Int <- 1 to 10){
      printTo = printTo + i + " "
    }
    println(printTo)

    var printUntil : String = "";
    for (i:Int <- 1 until 20 if i % 2 == 0){
      printUntil = printUntil + i + ""
    }
    println(printUntil)

    sayName()
    sayName("youtNa")
  }
}
