package moulton.thomas.weather_aggregator.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import moulton.thomas.weather_aggregator.datasource.CSVWeatherDataSource;
import moulton.thomas.weather_aggregator.datasource.JSONWeatherDataSource;
import moulton.thomas.weather_aggregator.datasource.OpenMeteoDataSource;
import moulton.thomas.weather_aggregator.datasource.WeatherDataAggregator;
import moulton.thomas.weather_aggregator.model.WeatherData;
import moulton.thomas.weather_aggregator.translator.OpenMetroDataTranslator;
import moulton.thomas.weather_aggregator.database.Database;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
	
    private final WeatherDataAggregator dataAggregator;
    private final Database database; // database instance added here

    public ApiController(WeatherDataAggregator dataAggregator, Database database) {
        this.dataAggregator = dataAggregator;
        this.database = database; // database instance initialization
    }

    @PostMapping("/upload-csv")
    public String uploadCsvData(@RequestParam("file") MultipartFile file) {
        try {
            InputStreamReader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
            CSVWeatherDataSource csvDataSource = new CSVWeatherDataSource(reader);
            List<WeatherData> rawData = csvDataSource.fetchData();

            // Save the data to the database using the WeatherDataAggregator class
            dataAggregator.saveToDatabase(rawData);

            return "CSV data uploaded and saved to the database!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload CSV data.";
        }
    }

    @PostMapping("/upload-json")
    public String uploadJsonData(@RequestParam("file") MultipartFile file) {
        try {
            InputStreamReader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
            JSONWeatherDataSource jsonDataSource = new JSONWeatherDataSource(reader);
            List<WeatherData> rawData = jsonDataSource.fetchData();

            // Save the data to the database using the WeatherDataAggregator class
            dataAggregator.saveToDatabase(rawData);

            return "JSON data uploaded and saved to the database!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload JSON data.";
        }
    }

    @GetMapping("/fetch-open-metro-today")
    public String fetchDataAndSaveToDatabase(@RequestParam double latitude, @RequestParam double longitude) {
        // Create a new OpenMeteoDataSource and OpenMetroDataTranslator for each request
        OpenMetroDataTranslator dataTranslator = new OpenMetroDataTranslator();
        OpenMeteoDataSource openMeteoDataSource = new OpenMeteoDataSource(latitude, longitude, dataTranslator);
        List<WeatherData> rawData = openMeteoDataSource.fetchData();

        // Save the translated data to the database using the WeatherDataAggregator class
        dataAggregator.saveToDatabase(rawData);

        return "Data fetched and saved to the database!";
    }
    
    @GetMapping("/all-weather-data")
    public List<WeatherData> getAllWeatherData() {
        return database.getAllWeatherData();
    }

    @GetMapping("/test")
    public String test() {
        return "API is working!";
    }
}
