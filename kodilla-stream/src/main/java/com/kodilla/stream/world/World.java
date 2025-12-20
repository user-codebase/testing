package com.kodilla.stream.world;

import java.math.BigDecimal;
import java.util.Set;

public final class World {

    private final Set<Continent> continents;

    public World(final Set<Continent> continents) {
        this.continents = continents;
    }

    public Set<Continent> getContinents() {
        return continents;
    }

    public BigDecimal getPeopleQuantity() {
        return continents.stream()
                .flatMap( continent -> continent.getCountries().stream())
                .map(Country::getPeopleQuantity)
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current));
    }
}
