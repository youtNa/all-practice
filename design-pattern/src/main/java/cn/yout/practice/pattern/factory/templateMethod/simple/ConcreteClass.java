package cn.yout.practice.pattern.factory.templateMethod.simple;

public class ConcreteClass extends AbstractClass{
    @Override
    public void primitiveOperationA() {
        System.out.println("方法A");
    }

    @Override
    public void primitiveOperationB() {
        System.out.println("方法B");
    }
}
