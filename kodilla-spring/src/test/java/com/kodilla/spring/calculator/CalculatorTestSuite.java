package com.kodilla.spring.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTestSuite {

    @Test
    void testCalculations() {
        // Given
        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.kodilla.spring");
        Calculator calculator = context.getBean(Calculator.class);

        // When
        double addResult = calculator.add(10, 5);
        double subResult = calculator.sub(10, 5);
        double mulResult = calculator.mul(10, 5);
        double divResult = calculator.div(10, 5);

        // Then
        assertEquals(15.0, addResult);
        assertEquals(5.0, subResult);
        assertEquals(50.0, mulResult);
        assertEquals(2.0, divResult);
    }

}
