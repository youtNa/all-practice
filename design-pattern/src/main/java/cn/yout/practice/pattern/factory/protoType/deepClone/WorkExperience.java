package cn.yout.practice.pattern.factory.protoType.deepClone;

public class WorkExperience implements Cloneable{
    private String workDate;
    private String company;

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public WorkExperience clone() throws CloneNotSupportedException{
        return (WorkExperience) super.clone();
    }

    @Override
    public String toString() {
        return workDate + "在" + company + "工作";
    }
}
