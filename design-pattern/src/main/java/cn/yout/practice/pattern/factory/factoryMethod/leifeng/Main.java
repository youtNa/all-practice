package cn.yout.practice.pattern.factory.factoryMethod.leifeng;

/**
 * 学习雷锋做好事。
 */
public class Main {
    public static void main(String[] args){
        //如果需要更换新的类型(学生或者社区志愿者)，只需要修改下面注释的代码即可。
//        LeifengFactory leifengFactory = new UndergraduateFactory();
        LeifengFactory leifengFactory = new VolunteerFactory();
        Leifeng leifeng = leifengFactory.createLeifeng();

        leifeng.sweep();
        leifeng.wash();

    }
}
