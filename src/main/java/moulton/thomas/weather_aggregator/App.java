package moulton.thomas.weather_aggregator;

import moulton.thomas.weather_aggregator.database.Database;
import moulton.thomas.weather_aggregator.datasource.*;
import moulton.thomas.weather_aggregator.model.WeatherData;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);

        Database db = context.getBean(Database.class);

        List<WeatherDataSource> sources = new ArrayList<>();

        for (WeatherDataSource source : sources) {
            List<WeatherData> data = source.fetchData();
            for (WeatherData weatherData : data) {
                // Other processing code...

                db.insertWeatherData(weatherData);
            }
        }
        
        List<WeatherData> dataFromDb = db.getAllWeatherData();
        for (WeatherData data : dataFromDb) {
            System.out.println(data);
        }
    }
}
