package moulton.thomas.weather_aggregator.datasource;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import moulton.thomas.weather_aggregator.model.WeatherData;

public class CSVWeatherDataSource implements WeatherDataSource {

    private String filePath;

    public CSVWeatherDataSource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<WeatherData> fetchData() {
        List<WeatherData> weatherDataList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CsvToBean<WeatherData> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(WeatherData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            weatherDataList = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherDataList;
    }
    
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
