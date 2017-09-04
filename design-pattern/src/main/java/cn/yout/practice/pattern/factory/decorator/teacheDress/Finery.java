package cn.yout.practice.pattern.factory.decorator.teacheDress;

public class Finery extends Person{
    private Person person;
    public void decorate(Person person){
        this.person = person;
    }

    @Override
    public void show() {
        if (person != null){
            person.show();
        }
    }
}
