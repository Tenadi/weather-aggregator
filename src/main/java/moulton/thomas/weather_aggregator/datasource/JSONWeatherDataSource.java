package moulton.thomas.weather_aggregator.datasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import moulton.thomas.weather_aggregator.model.WeatherData;

public class JSONWeatherDataSource implements WeatherDataSource {

    private InputStreamReader reader;

    public JSONWeatherDataSource(InputStreamReader reader) {
        this.reader = reader;
    }

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
