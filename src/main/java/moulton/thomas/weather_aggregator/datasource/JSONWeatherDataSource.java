package moulton.thomas.weather_aggregator.datasource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import moulton.thomas.weather_aggregator.model.WeatherData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * JSONWeatherDataSource is a class that implements the WeatherDataSource interface and fetches weather data
 * from a JSON file. It uses Gson for JSON parsing.
 * 
 * Author Thomas Moulton
 */
public class JSONWeatherDataSource implements WeatherDataSource {

    private InputStreamReader reader;

    /**
     * Constructs a new JSONWeatherDataSource instance using the provided reader.
     *
     * @param reader the InputStreamReader to use for reading the JSON data
     */
    public JSONWeatherDataSource(InputStreamReader reader) {
        this.reader = reader;
    }

    /**
     * Fetches the weather data from the JSON file, parsing the JSON into a list of WeatherData objects.
     *
     * @return a list of WeatherData objects representing the parsed JSON data
     */
    @Override
    public List<WeatherData> fetchData() {
        List<WeatherData> weatherDataList = new ArrayList<>();
        try (Reader reader = new BufferedReader(this.reader)) {
            weatherDataList = new Gson().fromJson(reader, new TypeToken<List<WeatherData>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherDataList;
    }
}
