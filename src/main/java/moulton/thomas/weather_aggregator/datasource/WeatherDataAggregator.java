package moulton.thomas.weather_aggregator.datasource;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import moulton.thomas.weather_aggregator.database.Database;
import moulton.thomas.weather_aggregator.model.WeatherData;

@Service
public class WeatherDataAggregator {

    private List<WeatherDataSource> dataSources;
    private Database database;

    public WeatherDataAggregator(List<WeatherDataSource> dataSources, Database database) {
        this.dataSources = dataSources;
        this.database = database;
    }

    public List<WeatherData> aggregateData() {
        List<WeatherData> aggregatedData = new ArrayList<>();
        for (WeatherDataSource dataSource : dataSources) {
            aggregatedData.addAll(dataSource.fetchData());
        }
        return aggregatedData;
    }

    public void saveToDatabase(List<WeatherData> weatherDataList) {
        for (WeatherData weatherData : weatherDataList) {
            database.insertWeatherData(weatherData);
        }
    }
}
