package cn.yout.practice.pattern.factory.protoType.shallowClone;

public class WorkExperience {
    private String workDate;
    private String company;

//    WorkExperience(String workDate,String company){
//        this.workDate = workDate;
//        this.company = company;
//    }

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
    public String toString() {
        return workDate + "在" + company + "工作";
    }
}
