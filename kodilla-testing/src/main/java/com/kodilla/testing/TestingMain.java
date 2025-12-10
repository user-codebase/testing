package com.kodilla.testing;
import com.kodilla.testing.calculator.Calculator;
import com.kodilla.testing.user.SimpleUser;

public class TestingMain {
    public static void main(String[] args) {

        // testing SimpleUser
        SimpleUser simpleUser = new SimpleUser("theForumUser");

        String result = simpleUser.getUsername();

        if (result.equals("theForumUser")) {
            System.out.println("test OK");
        } else {
            System.out.println("Error!");
        }

        // testing Calculator
        Calculator calculator = new Calculator();

        int sumResult = calculator.add(5, 10);
        int subtractResult = calculator.sub(5, 10);

        if (sumResult == 15) {
            System.out.println("test OK");
        }else  {
            System.out.println("Error!");
        }

        if (subtractResult == -5) {
            System.out.println("test OK");
        }else{
            System.out.println("Error!");
        }

    }
}
