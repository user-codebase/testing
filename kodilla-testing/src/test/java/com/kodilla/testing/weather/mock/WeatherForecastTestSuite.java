package com.kodilla.testing.weather.mock;

import com.kodilla.testing.weather.stub.Temperatures;
import com.kodilla.testing.weather.stub.WeatherForecast;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherForecastTestSuite {

    private static int testNumber = 0;

    @BeforeEach
    void beforeEach() {
        testNumber++;
        System.out.println("Test " + testNumber + " started");
    }

    @AfterEach
    void afterEach() {
        System.out.println("Map used for test nr " + testNumber + ": " + this.temperaturesMock.getTemperatures().toString());
        System.out.println("Test " + testNumber + " finished");
    }

    @Mock
    private Temperatures temperaturesMock;

    @Test
    void testCalculateForecastWithMock() {
        //Given
        Map<String, Double> temperaturesMap = new HashMap<>();
        temperaturesMap.put("Rzeszow", 25.5);
        temperaturesMap.put("Krakow", 26.2);
        temperaturesMap.put("Wroclaw", 24.8);
        temperaturesMap.put("Warszawa", 25.2);
        temperaturesMap.put("Gdansk", 26.1);
        when(temperaturesMock.getTemperatures()).thenReturn(temperaturesMap);
        WeatherForecast weatherForecast = new WeatherForecast(temperaturesMock);

        //When
        int quantityOfSensors = weatherForecast.calculateForecast().size();

        //Then
        Assertions.assertEquals(5, quantityOfSensors);
    }

    @Test
    void testCalculateAverageTemperature(){
        //Given
        Map<String, Double> temperaturesMap = new HashMap<>();
        temperaturesMap.put("New York", 17.1);
        temperaturesMap.put("Miami", 28.3);
        temperaturesMap.put("San Francisco", 24.8);
        temperaturesMap.put("Washington", 19.0);
        when(temperaturesMock.getTemperatures()).thenReturn(temperaturesMap);
        WeatherForecast weatherForecast = new WeatherForecast(temperaturesMock);

        //When
        double averageTemperature = weatherForecast.calculateAverageTemperature();

        //Then
        Assertions.assertEquals(22.3, averageTemperature);
    }

    @Test
    void testMedianTemperatureWithEvenNumberOfSensors(){
        //Given
        Map<String, Double> temperaturesMap = new HashMap<>();
        temperaturesMap.put("New York", 17.1);
        temperaturesMap.put("Miami", 28.3);
        temperaturesMap.put("San Francisco", 24.8);
        temperaturesMap.put("Washington", 19.0);
        when(temperaturesMock.getTemperatures()).thenReturn(temperaturesMap);
        WeatherForecast weatherForecast = new WeatherForecast(temperaturesMock);

        //When
        double medianTemperature = weatherForecast.calculateMedianTemperature();

        //Then
        Assertions.assertEquals(21.9, medianTemperature);
    }

    @Test
    void testMedianTemperatureWithOddNumberOfSensors(){
        //Given
        Map<String, Double> temperaturesMap = new HashMap<>();
        temperaturesMap.put("New York", 17.1);
        temperaturesMap.put("Miami", 28.3);
        temperaturesMap.put("Washington", 19.0);
        when(temperaturesMock.getTemperatures()).thenReturn(temperaturesMap);
        WeatherForecast weatherForecast = new WeatherForecast(temperaturesMock);

        //When
        double medianTemperature = weatherForecast.calculateMedianTemperature();

        //Then
        Assertions.assertEquals(19.0, medianTemperature);
    }
}
