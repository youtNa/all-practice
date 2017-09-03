package cn.yout.practice.pattern.factory.Decorator.TeacheDress;

public class Person {
    private String name;

    Person(){

    }

    Person(String name){
        this.name = name;
    }

    public void show(){
        System.out.println("装扮的" + name);
    }
}
