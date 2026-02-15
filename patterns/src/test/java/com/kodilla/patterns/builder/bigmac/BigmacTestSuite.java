package com.kodilla.patterns.builder.bigmac;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BigmacTestSuite {

    @Test
    void testBigmacBuilder() {
        //Given
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .bun("No sesame")
                .burgers(2)
                .sauce("1000 islands")
                .ingredient("Onion")
                .ingredient("Bacon")
                .ingredient("Chilli")
                .build();
        System.out.println(bigmac);

        //When
        String bun = bigmac.getBun();
        int numberOfBurgers = bigmac.getBurgers();
        String sauce = bigmac.getSauce();
        int numberOfIngredients = bigmac.getIngredients().size();

        //Then
        assertEquals("No sesame", bun);
        assertEquals(2, numberOfBurgers);
        assertEquals("1000 islands", sauce);
        assertTrue(bigmac.getIngredients().contains("Bacon"));
        assertEquals(3, numberOfIngredients);
    }

}
