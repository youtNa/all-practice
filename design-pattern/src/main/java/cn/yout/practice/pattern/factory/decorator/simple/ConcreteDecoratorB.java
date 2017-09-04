package cn.yout.practice.pattern.factory.decorator.simple;

public class ConcreteDecoratorB extends Decorator{
    @Override
    public void Operation() {
        super.Operation();
        AddedBehavior();
        System.out.println("具体装饰对象B的操作");
    }

    private void AddedBehavior(){

    }
}
