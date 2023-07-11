package moulton.thomas.weather_aggregator.api;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import moulton.thomas.weather_aggregator.datasource.*;
import moulton.thomas.weather_aggregator.model.WeatherData;
import moulton.thomas.weather_aggregator.translator.OpenMetroDataTranslator;
import moulton.thomas.weather_aggregator.database.Database;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * ApiController is a REST controller that exposes endpoints for interacting with 
 * weather data. This includes uploading data from CSV or JSON files, fetching 
 * data from the OpenMeteo API based on latitude and longitude, and retrieving 
 * all weather data from the database.
 * 
 * author Thomas Moulton
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	
    private final WeatherDataAggregator dataAggregator;
    private final Database database;

    /**
     * Constructs an ApiController with the provided WeatherDataAggregator and Database.
     *
     * @param dataAggregator the weather data aggregator
     * @param database the database instance
     */
    public ApiController(WeatherDataAggregator dataAggregator, Database database) {
        this.dataAggregator = dataAggregator;
        this.database = database; // database instance initialisation
    }

    /**
     * Uploads a CSV file containing weather data, and saves the data to the database.
     * 
     * @param file the uploaded CSV file
     * @return a status message indicating success or failure
     */
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

    /**
     * Uploads a JSON file containing weather data, and saves the data to the database.
     * 
     * @param file the uploaded JSON file
     * @return a status message indicating success or failure
     */
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

    /**
     * Fetches weather data from the OpenMeteo API based on the provided latitude and longitude,
     * translates the data, and saves it to the database.
     * 
     * @param latitude the latitude for the OpenMeteo API request
     * @param longitude the longitude for the OpenMeteo API request
     * @return a status message indicating success
     */
    @GetMapping("/fetch-open-metro-today")
    public String fetchDataAndSaveToDatabase(@RequestParam double latitude, @RequestParam double longitude) {
        // Create a new OpenMeteoDataSource and OpenMetroDataTranslator for each request
        OpenMetroDataTranslator dataTranslator = new OpenMetroDataTranslator();
        OpenMetaDataSource openMeteoDataSource = new OpenMetaDataSource(latitude, longitude, dataTranslator);
        List<WeatherData> rawData = openMeteoDataSource.fetchData();

        // Save the translated data to the database using the WeatherDataAggregator class
        dataAggregator.saveToDatabase(rawData);

        return "Data fetched and saved to the database!";
    }
    
    /**
     * Retrieves all weather data from the database.
     * 
     * @return a list of all weather data
     */
    @GetMapping("/all-weather-data")
    public List<WeatherData> getAllWeatherData() {
        return database.getAllWeatherData();
    }

    /**
     * Deletes all weather data in database.
     * 
     * @return a list of all weather data
     */
    @PostMapping("/delete-all-weather-data")
    public String deleteAllWeatherData() {
    	database.deleteAllWeatherData();
        return "Weather data deleted Successfully!";
    }
    
    /**
     * A test endpoint to check if the API is working.
     * 
     * @return a status message indicating that the API is working
     */
    @GetMapping("/test")
    public String test() {
        return "API is working!";
    }
}
