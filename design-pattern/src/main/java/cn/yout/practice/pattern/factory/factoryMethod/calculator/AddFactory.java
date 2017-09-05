package cn.yout.practice.pattern.factory.factoryMethod.calculator;

public class AddFactory implements IFactory{
    @Override
    public Operation createOperation() {
        return new OperationAdd();
    }
}
