package cn.yout.practice.pattern.factory.templateMethod.testPaper;

public class TestPaper {

    public void testQuestionA(){
        System.out.println("题目一");
        System.out.println("答案为："+ answerA());
    }

    public void testQuestionB(){
        System.out.println("题目二");
        System.out.println("答案为："+ answerB());
    }

    public String answerA(){
        return "";
    }

    public String answerB(){
        return "";
    }

}
