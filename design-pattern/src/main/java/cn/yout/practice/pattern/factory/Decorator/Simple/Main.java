package cn.yout.practice.pattern.factory.Decorator.Simple;

/**
 * 装饰器模式，基本结构
 */
public class Main {
    public static void main(String[] args){
        ConcreteComponent c = new ConcreteComponent();
        ConcreteDecoratorA d1 = new ConcreteDecoratorA();
        ConcreteDecoratorB d2 = new ConcreteDecoratorB();

        d1.SetComponent(c);
        d2.SetComponent(d1);
        d2.Operation();
    }
}
