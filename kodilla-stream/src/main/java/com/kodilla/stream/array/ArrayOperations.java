package com.kodilla.stream.array;

import java.util.Arrays;
import java.util.stream.IntStream;

public interface ArrayOperations {

    static double getAverage(int[] numbers){

        // display numbers
        IntStream.range(0,numbers.length).
                map(i -> numbers[i])
                .forEach(System.out::println);

        // calculate average
        return IntStream.range(0, numbers.length).
                map(i -> numbers[i])
                .average()
                .getAsDouble();


    }

}
