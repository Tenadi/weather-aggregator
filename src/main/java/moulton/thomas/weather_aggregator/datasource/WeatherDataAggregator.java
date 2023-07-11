package moulton.thomas.weather_aggregator.datasource;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import moulton.thomas.weather_aggregator.database.Database;
import moulton.thomas.weather_aggregator.model.WeatherData;

/**
 * WeatherDataAggregator is a class that fetches weather data from multiple data sources and
 * saves it into a database.
 * 
 * Author Thomas Moulton
 */
@Service
public class WeatherDataAggregator {

    private List<WeatherDataSource> dataSources;
    private Database database;

    /**
     * Constructs a new WeatherDataAggregator instance using the provided list of data sources and database.
     *
     * @param dataSources the list of data sources to fetch weather data from
     * @param database the database to save the fetched weather data into
     */
    public WeatherDataAggregator(List<WeatherDataSource> dataSources, Database database) {
        this.dataSources = dataSources;
        this.database = database;
    }

    /**
     * Aggregates weather data from all the data sources.
     *
     * @return a list of WeatherData objects representing the aggregated weather data
     */
    public List<WeatherData> aggregateData() {
        List<WeatherData> aggregatedData = new ArrayList<>();
        for (WeatherDataSource dataSource : dataSources) {
            aggregatedData.addAll(dataSource.fetchData());
        }
        return aggregatedData;
    }

    /**
     * Saves the provided list of WeatherData objects to the database.
     *
     * @param weatherDataList the list of WeatherData objects to save
     */
    public void saveToDatabase(List<WeatherData> weatherDataList) {
        for (WeatherData weatherData : weatherDataList) {
            database.insertWeatherData(weatherData);
        }
    }
}
