package moulton.thomas.weather_aggregator.datasource;

import java.util.List;
import java.util.ArrayList;

import moulton.thomas.weather_aggregator.model.WeatherData;

public class WeatherDataAggregator {

    private List<WeatherDataSource> dataSources;

    public WeatherDataAggregator(List<WeatherDataSource> dataSources) {
        this.dataSources = dataSources;
    }

    public List<WeatherData> aggregateData() {
        List<WeatherData> aggregatedData = new ArrayList<>();
        for (WeatherDataSource dataSource : dataSources) {
            aggregatedData.addAll(dataSource.fetchData());
        }
        return aggregatedData;
    }
}
