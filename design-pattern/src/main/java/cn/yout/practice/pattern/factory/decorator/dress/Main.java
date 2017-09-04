package cn.yout.practice.pattern.factory.decorator.dress;

/**
 * 依照基础结构和书上提供的题目自己编写的例子。
 */
public class Main {
    public static void main(String[] args){
        Person person = new Person("张三");
        Shoes shoes = new Shoes();
        Jacket jacket = new Jacket();

        shoes.setBody(person);
        jacket.setBody(shoes);
        jacket.dress();

    }
}
