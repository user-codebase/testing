package com.kodilla.testing.weather.stub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherForecast {

    private Temperatures temperatures;

    public WeatherForecast(Temperatures temperatures) {
        this.temperatures = temperatures;
    }

    public Map<String, Double> calculateForecast() {
        Map<String, Double> resultMap = new HashMap<>();

        for (Map.Entry<String, Double> temperature :
                temperatures.getTemperatures().entrySet()) {

            // adding 1 celsius degree to current value
            // as a temporary weather forecast
            resultMap.put(temperature.getKey(), temperature.getValue() + 1.0); // [1]
        }
        return resultMap;
    }

    public double calculateAverageTemperature(){
        double sumTempreatures = 0;
        for (Map.Entry<String, Double> mapEntry: temperatures.getTemperatures().entrySet()) {
            sumTempreatures += mapEntry.getValue();
        }
        System.out.println("sum of temperature: " + sumTempreatures); // just to visualize the value for test
        return sumTempreatures / temperatures.getTemperatures().size();
    }

    public double calculateMedianTemperature(){
        List<Double> listWithTemperatures = new ArrayList<>(temperatures.getTemperatures().values());
        listWithTemperatures.sort(null);
        System.out.println("listWithTemperatures: " + listWithTemperatures); // just to visualize the list for test

        int middleIndex = listWithTemperatures.size()/2;

        if(listWithTemperatures.size() % 2 == 0){
            return (listWithTemperatures.get(middleIndex - 1) + listWithTemperatures.get(middleIndex)) / 2;
        }else{
            return listWithTemperatures.get(middleIndex);
        }
    }

}
