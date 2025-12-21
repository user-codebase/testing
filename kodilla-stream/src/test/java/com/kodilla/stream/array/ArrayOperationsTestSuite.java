package com.kodilla.stream.array;

import org.junit.jupiter.api.*;

import java.util.Arrays;


public class ArrayOperationsTestSuite {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Test start");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Test end");
    }

    @Test
    void testGetAverage(){
        // Given
        int[] numbers = new int[]{2,5,8,3,4,9,1,7,8,2,3,4,8,4,6,5,8};

        // When
        double average = ArrayOperations.getAverage(numbers);

        // Then
        Assertions.assertEquals(5.1176470588,average, 0.0000000001);

    }

}
