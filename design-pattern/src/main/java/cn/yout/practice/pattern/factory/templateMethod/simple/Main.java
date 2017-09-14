package cn.yout.practice.pattern.factory.templateMethod.simple;

public class Main {
    public static void main(String[] args){
        AbstractClass classA = new ConcreteClass();
        classA.templateMethod();
    }
}
