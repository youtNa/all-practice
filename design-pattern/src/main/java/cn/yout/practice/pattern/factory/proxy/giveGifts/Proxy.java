package cn.yout.practice.pattern.factory.proxy.giveGifts;

public class Proxy implements IGIveGifts{
    private Pursuit pursuit;

    Proxy(String name){
        this.pursuit = new Pursuit(name);
    }

    @Override
    public void giveDolls() {
        pursuit.giveDolls();
    }

    @Override
    public void giveFlowers() {
        pursuit.giveFlowers();
    }
}
