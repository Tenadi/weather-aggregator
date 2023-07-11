package moulton.thomas.weather_aggregator.datasource;

import java.util.List;

import moulton.thomas.weather_aggregator.model.WeatherData;

/**
 * WeatherDataSource is an interface that represents a data source for fetching weather data.
 * It exposes a single method, fetchData, that any implementing class must define.
 * 
 * Author Thomas Moulton
 */
public interface WeatherDataSource {

    /**
     * Fetches weather data from the data source.
     *
     * @return a list of WeatherData objects representing the fetched weather data
     */
    List<WeatherData> fetchData();
}
