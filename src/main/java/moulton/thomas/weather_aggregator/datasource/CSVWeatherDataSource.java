package moulton.thomas.weather_aggregator.datasource;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import moulton.thomas.weather_aggregator.model.WeatherData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * CSVWeatherDataSource represents a data source that fetches weather data from CSV files.
 * It implements the WeatherDataSource interface, providing the fetchData method
 * to read the CSV file and parse it into a list of WeatherData objects.
 * 
 * Author Thomas Moulton
 */
public class CSVWeatherDataSource implements WeatherDataSource {

    private InputStreamReader reader;

    /**
     * Constructs a new CSVWeatherDataSource instance using the provided reader.
     *
     * @param reader the InputStreamReader to use for reading the CSV data
     */
    public CSVWeatherDataSource(InputStreamReader reader) {
        this.reader = reader;
    }

    /**
     * Fetches the weather data from the CSV file, parsing each line into a WeatherData object.
     *
     * @return a list of WeatherData objects representing the parsed CSV data
     */
    @Override
    public List<WeatherData> fetchData() {
        List<WeatherData> weatherDataList = new ArrayList<>();
        try (Reader reader = new BufferedReader(this.reader)) {
            CsvToBean<WeatherData> csvToBean = new CsvToBeanBuilder<WeatherData>(reader)
                    .withType(WeatherData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            weatherDataList = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherDataList;
    }

    /**
     * Parses a line from the CSV file into a WeatherData object.
     * This method assumes that the CSV fields are separated by commas.
     *
     * @param line the line from the CSV file to parse
     * @return a WeatherData object representing the parsed line
     */
    protected WeatherData parseLine(String line) {
        String[] fields = line.split(","); // assuming fields are separated by commas

        WeatherData data = new WeatherData();
        data.setLatitude(Double.parseDouble(fields[0].trim()));
        data.setLongitude(Double.parseDouble(fields[1].trim()));
        data.setUtcTime(fields[2].trim());
        data.setTemperature(Double.parseDouble(fields[3].trim()));
        data.setWindSpeed(Double.parseDouble(fields[4].trim()));
        data.setWindDirection(fields[5].trim());
        data.setPrecipitationChance(Double.parseDouble(fields[6].trim()));

        return data;
    }
}
