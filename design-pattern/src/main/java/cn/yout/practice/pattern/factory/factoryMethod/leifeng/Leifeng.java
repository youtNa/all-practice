package cn.yout.practice.pattern.factory.factoryMethod.leifeng;

public class Leifeng {
    private String type;
    Leifeng(String type){
        this.type = type;
    }

    protected void sweep(){
        System.out.println(type + "扫地");
    }
    protected void wash(){
        System.out.println(type + "洗衣");
    }
}
