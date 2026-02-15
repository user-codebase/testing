package com.kodilla.patterns.strategy.social;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTestSuite {

    @Test
    public void testDefaultSharingStrategies() {
        //Given
        User john = new Millenials("John");
        User eva = new YGeneration("Eva");
        User adam = new ZGeneration("Adam");
        //When
        System.out.println("John default action: " + john.sharePost());
        System.out.println("Eva default action: " + eva.sharePost());
        System.out.println("Adam default action: " + adam.sharePost());

        //Then
        assertEquals("Sharing post on Facebook", john.sharePost());
        assertEquals("Sharing post on Twitter", eva.sharePost());
        assertEquals("Sharing post on Snapchat", adam.sharePost());
    }

    @Test
    public void testIndividualSharingStrategy() {
        //Given
        User john = new Millenials("John");

        //When
        System.out.println("John default action: " + john.sharePost());
        john.setSocialPublisher(new TwitterPublisher());
        System.out.println("John action after change: " + john.sharePost());

        //Then
        assertEquals("Sharing post on Twitter", john.sharePost());
    }

}
