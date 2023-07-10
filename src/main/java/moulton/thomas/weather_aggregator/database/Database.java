package moulton.thomas.weather_aggregator.database;

import moulton.thomas.weather_aggregator.model.WeatherData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class Database {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<WeatherData> weatherDataRowMapper = (rs, rowNum) -> {
        WeatherData data = new WeatherData();
        data.setLatitude(rs.getDouble("latitude"));
        data.setLongitude(rs.getDouble("longitude"));
        data.setUtcTime(rs.getString("utc_time"));
        data.setTemperature(rs.getInt("temperature"));
        data.setWindSpeed(rs.getInt("wind_speed"));
        data.setWindDirection(rs.getString("wind_direction"));
        data.setPrecipitationChance(rs.getDouble("precipitation_chance"));

        return data;
    };

    public Database(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertWeatherData(WeatherData data) {
        try {
            if (!doesWeatherDataExist(data)) { // check if the data already exists
                String sql = "INSERT INTO weather_data (latitude, longitude, utc_time, temperature, wind_speed, wind_direction, precipitation_chance) VALUES (?, ?, ?, ?, ?, ?, ?)";

                // convert utc_time from String to Timestamp
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                java.util.Date parsedDate = dateFormat.parse(data.getUtcTime());
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

                jdbcTemplate.update(sql, data.getLatitude(), data.getLongitude(), timestamp, data.getTemperature(), data.getWindSpeed(), data.getWindDirection(), data.getPrecipitationChance());
            }
        } catch(Exception e) {
            // handle exception
            e.printStackTrace();
        }
    }

    // check if the WeatherData record already exists
    public boolean doesWeatherDataExist(WeatherData data) {
        String sql = "SELECT COUNT(*) FROM weather_data WHERE latitude = ? AND longitude = ? AND utc_time = ?";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            java.util.Date parsedDate = dateFormat.parse(data.getUtcTime());
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            Integer count = jdbcTemplate.queryForObject(sql, new Object[]{data.getLatitude(), data.getLongitude(), timestamp}, Integer.class);
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<WeatherData> getAllWeatherData() {
        String sql = "SELECT latitude, longitude, utc_time, temperature, wind_speed, wind_direction, precipitation_chance FROM weather_data";
        return jdbcTemplate.query(sql, weatherDataRowMapper);
    }

    public void deleteAllWeatherData() {
        String sql = "DELETE FROM weather_data";
        jdbcTemplate.update(sql);
    }
}
