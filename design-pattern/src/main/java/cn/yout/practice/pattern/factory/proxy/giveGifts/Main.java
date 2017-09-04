package cn.yout.practice.pattern.factory.proxy.giveGifts;

/**
 * 代理模式，代送礼物
 *
 */
public class Main {
    public static void main(String[] args){
        Proxy proxy = new Proxy("beautiful girl");

        proxy.giveDolls();
        proxy.giveFlowers();
    }
}
