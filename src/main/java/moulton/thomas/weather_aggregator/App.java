package moulton.thomas.weather_aggregator;

import moulton.thomas.weather_aggregator.database.Database;
import moulton.thomas.weather_aggregator.model.WeatherData;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Main application class for the Weather Aggregator. 
 * The application collects weather data from multiple data sources and 
 * stores it into a database. It then retrieves the stored data.
 * 
 * This is achieved via a RESTful api, controlled by the ApiController.
 * 
 * This project is intended to be run within a docker container.
 * 
 * Author Thomas Moulton
 */

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        // Initialise Spring Application context
        ApplicationContext context = SpringApplication.run(App.class, args);

        // Obtain a Database bean from the application context
        Database db = context.getBean(Database.class);

        // Retrieve all Weather Data from the Database and print to console
        List<WeatherData> dataFromDb = db.getAllWeatherData();
        for (WeatherData data : dataFromDb) {
            System.out.println(data);
        }
    }
}
