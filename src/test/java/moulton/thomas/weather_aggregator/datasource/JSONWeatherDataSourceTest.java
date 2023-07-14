package moulton.thomas.weather_aggregator.datasource;

import org.junit.jupiter.api.Test;

import moulton.thomas.weather_aggregator.model.WeatherData;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JSONWeatherDataSourceTest {

    @Test
    public void testFetchData() throws IOException {
        // Read the JSON file into an InputStream
        InputStream inputStream = getClass().getResourceAsStream("/baseJsonTestData.json");

        // Create an InputStreamReader from the InputStream
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        // Create the JSONWeatherDataSource using the InputStreamReader
        JSONWeatherDataSource dataSource = new JSONWeatherDataSource(reader);

        // Fetch the weather data
        List<WeatherData> weatherDataList = dataSource.fetchData();

        // Assertions
        assertNotNull(weatherDataList);
        assertFalse(weatherDataList.isEmpty());
        assertEquals(3, weatherDataList.size());

        WeatherData weatherData1 = weatherDataList.get(0);
        assertEquals(51.5074, weatherData1.getLatitude());
        assertEquals(-0.1278, weatherData1.getLongitude());
        assertEquals("2023-07-10T12:00", weatherData1.getUtcTime());
        assertEquals(25, weatherData1.getTemperature());
        assertEquals(10, weatherData1.getWindSpeed());
        assertEquals(1.0, weatherData1.getWindDirection());
        assertEquals(0.15, weatherData1.getPrecipitationChance());
    }
}
