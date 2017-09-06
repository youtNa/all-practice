package cn.yout.practice.pattern.factory.protoType.shallowClone;

public class Resume implements Cloneable{
    private int sex;
    private int age;
    private String name;
    private WorkExperience workExperience;
    Resume(){
        this.workExperience = new WorkExperience();
    }

    public void setPersonInfo(int sex, int age, String name){
        this.sex = sex;
        this.age = age;
        this.name = name;
    }

    public void setWorkExperience(String workDate,String company){
        workExperience.setWorkDate(workDate);
        workExperience.setCompany(company);
    }

    @Override
    public Resume clone() throws CloneNotSupportedException{
        return (Resume)super.clone();
    }

    public void display(){
        System.out.println(this.toString());
        System.out.println(this.workExperience.toString());
    }

    @Override
    public String toString() {
        return name + "性别:" + sex + "年龄:" + age + "岁";
    }
}
