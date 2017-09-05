package cn.yout.practice.pattern.factory.factoryMethod.calculator;

public class Main {
    public static void main(String[] args){
        IFactory iFactory = new AddFactory();
        Operation operation = iFactory.createOperation();

        operation.setNumberA(5);
        operation.setNumberB(6);
        int result = operation.getResult();
        System.out.println(result);
    }
}
