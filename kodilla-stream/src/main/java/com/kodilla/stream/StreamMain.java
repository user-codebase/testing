package com.kodilla.stream;

import com.kodilla.stream.beautifier.PoemBeautifier;
import com.kodilla.stream.lambda.ExecuteSaySomething;
import com.kodilla.stream.lambda.ExpressionExecutor;
import com.kodilla.stream.lambda.Processor;
import com.kodilla.stream.lambda.SaySomething;
import com.kodilla.stream.reference.FunctionalCalculator;

public class StreamMain {
    public static void main(String[] args) {
        System.out.println("Welcome to module 7 - Stream");

        SaySomething saySomething = new SaySomething();
        saySomething.say();

        Processor processor = new Processor();
        processor.execute(() -> System.out.println("This is an example test."));

        ExpressionExecutor expressionExecutor = new ExpressionExecutor();

        expressionExecutor.executeExpression(10, 5, (a, b) -> a + b);
        expressionExecutor.executeExpression(10, 5, (a, b) -> a - b);
        expressionExecutor.executeExpression(10, 5, (a, b) -> a * b);
        expressionExecutor.executeExpression(10, 5, (a, b) -> a / b);

        expressionExecutor.executeExpression(10, 5, FunctionalCalculator::addAToB);
        expressionExecutor.executeExpression(10, 5, FunctionalCalculator::subBFromA);
        expressionExecutor.executeExpression(10, 5, FunctionalCalculator::multiplyAByB);
        expressionExecutor.executeExpression(10, 5, FunctionalCalculator::divideAByB);



        // Zadanie 7.1
        PoemBeautifier poemBeautifier = new PoemBeautifier();

        // Example 1
        String beautifulText1 = poemBeautifier.beautify("Hello World", txt -> "ABC" + txt + "ABC");
        System.out.println("beautifulText1: " + beautifulText1);

        // Example 2
        String beautifulText2 = poemBeautifier.beautify("Hello World", String::toUpperCase);
        System.out.println("beautifulText2: " + beautifulText2);

        // Example 3
        String beautifulText3 = poemBeautifier.beautify("Hello World", txt -> {
            char[] chars = txt.toCharArray();
            String[] arrayWithString = new String[chars.length];
            for (int i = 0; i < chars.length; i++) {
                arrayWithString[i] = String.valueOf(chars[i]);
            }
            return String.join("-", arrayWithString);
        });
        System.out.println("beautifulText3: " + beautifulText3);


        // Example 4
        String beautifulText4 = poemBeautifier.beautify("Hello World", txt -> {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < txt.length(); i++) {
                if (i%2 == 0) builder.append(Character.toUpperCase(txt.charAt(i)));
                else builder.append(Character.toLowerCase(txt.charAt(i)));
            }
            return builder.toString();
        });
        System.out.println("beautifulText4: " + beautifulText4);


    }
}
