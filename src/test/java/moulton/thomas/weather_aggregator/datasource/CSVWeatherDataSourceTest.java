package moulton.thomas.weather_aggregator.datasource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

import moulton.thomas.weather_aggregator.model.WeatherData;

public class CSVWeatherDataSourceTest {
    @Test
    public void testParseLine() {
        CSVWeatherDataSource dataSource = new CSVWeatherDataSource("test.csv");

        String line = "40.71, -74.01, 2023-07-07T16:00:00Z, 29, 5, North, 0.2";
        WeatherData data = dataSource.parseLine(line);

        assertEquals(40.71, data.getLatitude());
        assertEquals(-74.01, data.getLongitude());
        assertEquals("2023-07-07T16:00:00Z", data.getUtcTime());
        assertEquals(29, data.getTemperature());
        assertEquals(5, data.getWindSpeed());
        assertEquals("North", data.getWindDirection());
        assertEquals(0.2, data.getPrecipitationChance());
        
        System.out.println(data.toString());
    }

    @Test
    public void testParseInvalidLine() {
        final CSVWeatherDataSource dataSource = new CSVWeatherDataSource("test.csv");
        final String line = "invalid, line, data";

        Executable executable = new Executable() {
            @Override
            public void execute() throws Throwable {
                dataSource.parseLine(line);
            }
        };

        assertThrows(IllegalArgumentException.class, executable);
    }


}
