package me.amenable.java8to11.ch01;

public class Foo {

    public static void main(String[] args) {
//        RunSomething runSomething = () -> {
//            System.out.println("start doIt method");
//        };
//
//        runSomething.doIt();

        RunSomething runSomething = (number) -> {
            System.out.println(number + 10);
            return number;
        };

        runSomething.doIt(10);
        runSomething.doIt(20);
        runSomething.doIt(30);
    }
}
