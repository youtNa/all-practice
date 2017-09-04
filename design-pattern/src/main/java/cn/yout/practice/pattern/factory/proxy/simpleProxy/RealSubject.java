package cn.yout.practice.pattern.factory.proxy.simpleProxy;

public class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println("真实的请求");
    }
}
