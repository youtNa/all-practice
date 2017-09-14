package cn.yout.practice.pattern.factory.templateMethod.simple;

public abstract class AbstractClass {
    public abstract void primitiveOperationA();
    public abstract void primitiveOperationB();

    public void templateMethod(){
        primitiveOperationA();
        primitiveOperationB();
        System.out.print("templateMethod");
    }

}
