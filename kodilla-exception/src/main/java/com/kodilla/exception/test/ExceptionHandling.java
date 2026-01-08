package com.kodilla.exception.test;

public class ExceptionHandling {

    public void testSecondChallenge(double x, double y) {

        SecondChallenge secondChallenge = new SecondChallenge();
        String arguments = "x: " + x + ", y: " + y;

        try{
            String result = secondChallenge.probablyIWillThrowException(x,y);
            System.out.println("result: " + result);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(arguments);
        }finally {
            System.out.println("testSecondChallenge() completed.");
        }
    }

}
