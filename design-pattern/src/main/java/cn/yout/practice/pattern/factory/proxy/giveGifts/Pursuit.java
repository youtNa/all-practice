package cn.yout.practice.pattern.factory.proxy.giveGifts;

public class Pursuit implements IGIveGifts{
    String name;
    Pursuit(String name){
        this.name = name;
    }

    @Override
    public void giveDolls() {
        System.out.println(name + " 送你娃娃");
    }

    @Override
    public void giveFlowers() {
        System.out.println(name + " 送你花");
    }
}
