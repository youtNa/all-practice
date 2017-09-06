package cn.yout.practice.pattern.factory.protoType.shallowClone;

/**
 * 原型模式，浅复制（浅克隆），只复制对象，不复制引用。
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Resume resume = new Resume();
        resume.setPersonInfo(1,12,"小李");
        resume.setWorkExperience("2013","微软");
//        resume.display();

        Resume resume1 = resume.clone();
        resume1.setPersonInfo(2,16,"小张");
//        resume.display();

        Resume resume2 = resume.clone();
        resume2.setWorkExperience("2014","Google");

        resume.display();
        resume1.display();
        resume2.display();
    }
}
