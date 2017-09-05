package cn.yout.practice.pattern.factory.factoryMethod.leifeng;

public class VolunteerFactory implements LeifengFactory{
    @Override
    public Leifeng createLeifeng() {
        //一下两种方式均可实例化所需的类。
//        return new Volunteer();
        return new Leifeng("社区志愿者");
    }
}
