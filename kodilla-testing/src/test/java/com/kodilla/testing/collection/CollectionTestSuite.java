package com.kodilla.testing.collection;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionTestSuite {

    private List<Integer> list;
    private static int testNumber;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Test Suite: Unit Tests to test List<Integer> - Start:");
        CollectionTestSuite.testNumber = 0;
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Test Suite: Unit Tests to test List<Integer> - End:");
    }

    @BeforeEach
    public void beforeEach() {
        CollectionTestSuite.testNumber++;
        this.list = new ArrayList<>();
        System.out.println("Unit Test number: " + CollectionTestSuite.testNumber);

    }

    @AfterEach
    public void afterEach() {
        System.out.println("Unit Test end.");
    }


    @DisplayName("Unit Test 1: when argument sent to method is empty list, then returned object should be empty list as well.")

    @Test
    public void testOddNumbersExterminatorEmptyList(){

        //Given
        this.list = new ArrayList<>();
        OddNumbersExterminator oddNumbersExterminator = new OddNumbersExterminator();

        //When
        List<Integer> returnList = oddNumbersExterminator.exterminate(this.list);

        //Then
        Assertions.assertTrue(returnList.isEmpty());
    }

    @DisplayName("Unit Test 2: when argument sent to method is list including both odd and even numbers, then " +
            "returned object should be list including even numbers exclusively")

    @Test
    public void testOddNumbersExterminatorNormalList(){

        //Given
        this.list.addAll(Arrays.asList(1,5,2,8,3,9,11,10,30,25,65,23,76,87));
        OddNumbersExterminator oddNumbersExterminator = new OddNumbersExterminator();

        //When
        List<Integer> returnList = oddNumbersExterminator.exterminate(this.list);
        List<Integer> expectedList = new ArrayList<>(Arrays.asList(2,8,10,30,76));

        //Then
        Assertions.assertEquals(expectedList, returnList);
    }


}
