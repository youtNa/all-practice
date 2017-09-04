package cn.yout.practice.pattern.factory.proxy.simpleProxy;

public class Proxy implements Subject{
    private RealSubject realSubject;

    @Override
    public void request() {
        if (realSubject == null){
            realSubject = new RealSubject();
        }
        realSubject.request();
    }
}
