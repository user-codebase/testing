package com.kodilla.spring.library;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LibraryTestSuite.TestConfig.class)
class LibraryTestSuite {

    @Autowired
    private Library library;

//    @Test
//    void testLoadFromDb() {
//        //Given
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext("com.kodilla.spring");
//        Library library = context.getBean(Library.class);
//
//        //When
//        library.loadFromDb();
//
//        //Then
//        //do nothing
//    }
//
//    @Test
//    void testSaveToDb() {
//        //Given
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext("com.kodilla.spring");
//        Library library = context.getBean(Library.class);
//
//        //When
//        library.saveToDb();
//
//        //Then
//        //do nothing
//    }
    @Test
    public void testLoadFromDb() {
        //Given
        //When
        library.loadFromDb();
        //Then
        //do nothing
    }

    @Test
    public void testSaveToDb() {
        //Given
        //When
        library.saveToDb();
        //Then
        //do nothing
    }

    @Configuration
    @ComponentScan("com.kodilla.spring")
    static class TestConfig {
    }
}