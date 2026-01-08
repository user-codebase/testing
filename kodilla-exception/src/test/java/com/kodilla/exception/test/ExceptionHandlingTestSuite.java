package com.kodilla.exception.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionHandlingTestSuite {

    @Test
    public void testExceptionHandling() {

        // given
        SecondChallenge challenge = new SecondChallenge();

        // when & then
        Assertions.assertDoesNotThrow(() -> challenge.probablyIWillThrowException(1.5, 2.5));
        Assertions.assertDoesNotThrow(() -> challenge.probablyIWillThrowException(1.99, 1.4));
        Assertions.assertThrows(Exception.class, () -> challenge.probablyIWillThrowException(2.0, 1.5));
        Assertions.assertThrows(Exception.class, () -> challenge.probablyIWillThrowException(1.5, 1.5));
        Assertions.assertThrows(Exception.class, () -> challenge.probablyIWillThrowException(0.99, 4.0));

    }
}
