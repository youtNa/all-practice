package cn.yout.practice.pattern.factory.templateMethod.testPaper;

public class TestPaperB extends TestPaper{
    @Override
    public String answerA() {
        return "A";
    }

    @Override
    public String answerB() {
        return "C";
    }
}
