package com.kodilla.stream.world;

import java.util.Objects;
import java.util.Set;

public final class Continent {

    private final String name;
    private final Set<Country> countries;

    public Continent(final String name, final Set<Country> countries) {
        this.name = name;
        this.countries = countries;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Continent continent = (Continent) o;
        return Objects.equals(name, continent.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
