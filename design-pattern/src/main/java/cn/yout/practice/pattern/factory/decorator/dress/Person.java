package cn.yout.practice.pattern.factory.decorator.dress;

public class Person extends Body{
    private String name;
    Person(String name){
        this.name = name;
    }

    @Override
    public void dress() {
        System.out.print("装扮的" + name );
    }
}
