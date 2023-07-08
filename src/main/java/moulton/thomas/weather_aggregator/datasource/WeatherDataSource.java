package moulton.thomas.weather_aggregator.datasource;

import java.util.List;

import moulton.thomas.weather_aggregator.model.WeatherData;

public interface WeatherDataSource {
    List<WeatherData> fetchData();
}
