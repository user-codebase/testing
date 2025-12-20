package com.kodilla.stream.world;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class WorldTestSuite {

    @Test
    void testGetPeopleQuantity(){
        /*/////////////////// GIVEN ///////////////////*/

        //countries - Europe
        Country germany = new Country("Germany", new BigDecimal("84075075"));
        Country france = new Country("France", new BigDecimal("66650804"));
        Country italy = new Country("Italy", new BigDecimal("59146260"));
        Country spain = new Country("Spain", new BigDecimal("47889958"));

        //countries - Asia
        Country india = new Country("india", new BigDecimal("1463865525"));
        Country china = new Country("china", new BigDecimal("1416096094"));
        Country indonesia = new Country("indonesia", new BigDecimal("285721236"));
        Country pakistan = new Country("pakistan", new BigDecimal("255219554"));

        //countries - North America
        Country usa = new Country("USA", new BigDecimal("347275807"));
        Country mexico = new Country("Mexico", new BigDecimal("129035733"));
        Country canada = new Country("Canada", new BigDecimal("40126723"));
        Country guatemala = new Country("Guatemala", new BigDecimal("17602431"));


        //set-europe
        Set<Country> countriesEurope = new HashSet<>();
        countriesEurope.add(germany);
        countriesEurope.add(france);
        countriesEurope.add(italy);
        countriesEurope.add(spain);

        //set-asia
        Set<Country> countriesAsia = new HashSet<>();
        countriesAsia.add(india);
        countriesAsia.add(china);
        countriesAsia.add(indonesia);
        countriesAsia.add(pakistan);

        //set-north-america
        Set<Country> countriesNorthAmerica = new HashSet<>();
        countriesNorthAmerica.add(usa);
        countriesNorthAmerica.add(mexico);
        countriesNorthAmerica.add(canada);
        countriesNorthAmerica.add(guatemala);

        //continents
        Continent europe = new Continent("Europe", countriesEurope);
        Continent asia = new Continent("Asia", countriesAsia);
        Continent northAmerica = new Continent("North America", countriesNorthAmerica);

        /*/////////////////// WHEN ///////////////////*/
        Set<Continent> continents = new HashSet<>();
        continents.add(europe);
        continents.add(asia);
        continents.add(northAmerica);
        World world = new World(continents);
        BigDecimal peopleQuantity = world.getPeopleQuantity();


        /*/////////////////// THEN ///////////////////*/
        BigDecimal expectedPeopleQuantity = new BigDecimal("4212705200");
        Assertions.assertEquals(expectedPeopleQuantity, peopleQuantity);


    }

}
