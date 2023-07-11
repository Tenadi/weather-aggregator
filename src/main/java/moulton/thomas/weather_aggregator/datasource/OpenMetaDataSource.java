package moulton.thomas.weather_aggregator.datasource;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import moulton.thomas.weather_aggregator.model.WeatherData;
import moulton.thomas.weather_aggregator.translator.OpenMetroDataTranslator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * OpenMeteoDataSource is a class that implements the WeatherDataSource interface and fetches weather data
 * from the Open-Meteo API. It uses Gson for JSON parsing and an instance of OpenMetroDataTranslator for translating
 * the received data into a format suitable for the application.
 * 
 * Author Thomas Moulton
 */
public class OpenMetaDataSource implements WeatherDataSource {

    private final double latitude;
    private final double longitude;
    private final OpenMetroDataTranslator dataTranslator;

    /**
     * Constructs a new OpenMeteoDataSource instance using the provided latitude, longitude and data translator.
     *
     * @param latitude the latitude coordinate for the location to fetch weather data for
     * @param longitude the longitude coordinate for the location to fetch weather data for
     * @param dataTranslator the OpenMetroDataTranslator to use for translating the API response
     */
    public OpenMetaDataSource(double latitude, double longitude, OpenMetroDataTranslator dataTranslator) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataTranslator = dataTranslator;
    }

    /**
     * Fetches the weather data from the Open-Meteo API, parsing the JSON response and translating it
     * into a list of WeatherData objects.
     *
     * @return a list of WeatherData objects representing the translated API response
     */
    @Override
    public List<WeatherData> fetchData() {
        List<WeatherData> weatherDataList = new ArrayList<>();

        try {
            // Build the API URL with the provided latitude and longitude
            String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude +
                    "&hourly=temperature_2m,precipitation,windspeed_180m,winddirection_180m&forecast_days=1";

            // Make an HTTP request to the Open-Meteo API
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response from the API
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // Parse the JSON response
            JsonParser parser = new JsonParser();
            JsonObject jsonData = parser.parse(response.toString()).getAsJsonObject();

            // Translate the data
            weatherDataList = dataTranslator.translateData(jsonData);

        } catch (IOException e) {
            // Handle any exceptions that occur during the API request or response parsing
            e.printStackTrace();
        }

        return weatherDataList;
    }
}
