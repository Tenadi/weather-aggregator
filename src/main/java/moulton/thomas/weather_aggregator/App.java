package moulton.thomas.weather_aggregator;

import moulton.thomas.weather_aggregator.database.Database;
import moulton.thomas.weather_aggregator.datasource.*;
import moulton.thomas.weather_aggregator.model.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<WeatherDataSource> sources = new ArrayList<>();
        sources.add(new CSVWeatherDataSource("path_to_your_csv_file.csv"));
        sources.add(new JSONWeatherDataSource("path_to_your_json_file.json"));

        Database db = new Database();

        for (WeatherDataSource source : sources) {
            List<WeatherData> data = source.fetchData();
            for (WeatherData weatherData : data) {
                // Other processing code...

                db.insertWeatherData(weatherData);
            }
        }
    }
}
