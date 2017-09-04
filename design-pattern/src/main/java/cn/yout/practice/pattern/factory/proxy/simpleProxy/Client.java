package cn.yout.practice.pattern.factory.proxy.simpleProxy;

/**
 * 代理模式，简单例子
 */
public class Client {
    public static void main(String[] args){
        Proxy proxy = new Proxy();

        proxy.request();
    }
}
