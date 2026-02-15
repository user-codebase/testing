package com.kodilla.patterns.singleton;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoggerTestSuite {

    private static Logger logger;

    @BeforeAll
    static void beforeAll() {
//        logger = new Logger();
        logger = Logger.INSTANCE;
    }

    @Test
    void testLogSingleton() {
        //Given
        //When
        logger.log("First log");
        logger.log("Second log");
        String lastLog = logger.getLastLog();

        //Then
        assertEquals("Second log", lastLog);
    }

    @Test
    void testLogSingleton_v2() {
        //Given
        //When
        logger.log("Another log");
        String lastLog = logger.getLastLog();

        //Then
        assertEquals("Another log", lastLog);
    }



}
