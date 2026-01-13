package com.kodilla.exception.test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class FlightManager {

    public void findFlight(Flight flight) throws RouteNotFoundException{

        Map<String, Boolean> flightMap = new HashMap<>();
        IntStream.range(0, 30).forEach(i -> flightMap.put("Airport " + i, i%2 == 0));

        if (!flightMap.keySet().contains(flight.getArrivalAirport())) {
            throw new RouteNotFoundException("Arrival airport (" + flight.getArrivalAirport() + ") does not exist.");
        }
        if (!flightMap.keySet().contains(flight.getDepartureAirport())) {
            throw new RouteNotFoundException("Departure airport (" + flight.getDepartureAirport() + ") does not exist.");
        }

        if (!flightMap.get(flight.getArrivalAirport())) {
            System.out.println("Arrival airport (" + flight.getArrivalAirport() + ") is not available.");
        } else {
            System.out.println("Arrival airport (" + flight.getArrivalAirport() + ") is available.");
        }

        if (!flightMap.get(flight.getDepartureAirport())) {
            System.out.println("Departure airport (" + flight.getDepartureAirport() + ") is not available.");
        } else {
            System.out.println("Departure airport (" + flight.getDepartureAirport() + ") is available.");
        }

    }

    public static void main(String[] args) {
        FlightManager fm = new FlightManager();
        try {
            fm.findFlight(new Flight("Airport 21", "Airport 2"));
        }catch (RouteNotFoundException e){
            System.out.println(e.getMessage());
        }finally{
            System.out.println("Airport check has been finished.");
        }
    }

}
