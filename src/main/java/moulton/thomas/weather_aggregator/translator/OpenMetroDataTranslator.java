package moulton.thomas.weather_aggregator.translator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import moulton.thomas.weather_aggregator.model.WeatherData;

import java.util.ArrayList;
import java.util.List;

/**
 * OpenMetroDataTranslator class is used to translate raw JSON data from Open-Meteo's API into a list of WeatherData objects.
 * 
 * Author Thomas Moulton
 */
public class OpenMetroDataTranslator {

    /**
     * Translates the provided JSON data from the Open-Meteo API into a list of WeatherData objects.
     *
     * @param jsonData the JSON data returned from the Open-Meteo API
     * @return a list of WeatherData objects representing the data provided by the API
     */
    public List<WeatherData> translateData(JsonObject jsonData) {
        List<WeatherData> weatherDataList = new ArrayList<>();

        double apiLatitude = jsonData.get("latitude").getAsDouble();
        double apiLongitude = jsonData.get("longitude").getAsDouble();
        JsonObject hourly = jsonData.getAsJsonObject("hourly");
        JsonArray timeArray = hourly.getAsJsonArray("time");
        JsonArray temperatureArray = hourly.getAsJsonArray("temperature_2m");
        JsonArray precipitationArray = hourly.getAsJsonArray("precipitation");
        JsonArray windspeedArray = hourly.getAsJsonArray("windspeed_180m");
        JsonArray winddirectionArray = hourly.getAsJsonArray("winddirection_180m");

        // For each time interval, create a WeatherData object and add it to the list
        for (int i = 0; i < timeArray.size(); i++) {
            WeatherData weatherData = new WeatherData();
            weatherData.setLatitude(apiLatitude);
            weatherData.setLongitude(apiLongitude);
            weatherData.setUtcTime(timeArray.get(i).getAsString());
            weatherData.setTemperature(temperatureArray.get(i).getAsDouble());
            weatherData.setPrecipitationChance(precipitationArray.get(i).getAsDouble());
            weatherData.setWindSpeed(windspeedArray.get(i).getAsDouble());
            weatherData.setWindDirection(winddirectionArray.get(i).getAsDouble());

            weatherDataList.add(weatherData);
        }

        return weatherDataList;
    }
}
