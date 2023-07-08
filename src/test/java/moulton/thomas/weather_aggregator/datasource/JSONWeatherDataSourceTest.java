package moulton.thomas.weather_aggregator.datasource;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import moulton.thomas.weather_aggregator.model.WeatherData;

public class JSONWeatherDataSourceTest {
    @Test
    public void testGetData() {
    	
        JSONWeatherDataSource dataSource = new JSONWeatherDataSource("src\\test\\resources\\baseJsonTestData.json");
        List<WeatherData> data = dataSource.fetchData();

        assertEquals(1, data.size());

        WeatherData weatherData = data.get(0);

        assertEquals(40.71, weatherData.getLatitude());
        assertEquals(-74.01, weatherData.getLongitude());
        assertEquals("2023-07-07T16:00:00Z", weatherData.getUtcTime());
        assertEquals(29, weatherData.getTemperature());
        assertEquals(5, weatherData.getWindSpeed());
        assertEquals("North", weatherData.getWindDirection());
        assertEquals(0.2, weatherData.getPrecipitationChance());
        
        System.out.println(data.toString());
    }
}
