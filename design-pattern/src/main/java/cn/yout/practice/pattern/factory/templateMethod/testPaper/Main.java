package cn.yout.practice.pattern.factory.templateMethod.testPaper;

public class Main {
    public static void main(String[] args){
        TestPaper studentA = new TestPaperA();
        TestPaper studentB = new TestPaperB();

        studentA.testQuestionA();
        studentA.testQuestionB();

        studentB.testQuestionA();
        studentB.testQuestionB();
    }
}
