package cn.yout.practice.pattern.factory.templateMethod.testPaper;

public class TestPaperA extends TestPaper{
    @Override
    public String answerA() {
        return "C";
    }

    @Override
    public String answerB() {
        return "D";
    }
}
