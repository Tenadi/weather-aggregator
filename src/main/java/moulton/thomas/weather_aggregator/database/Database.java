package moulton.thomas.weather_aggregator.database;

import moulton.thomas.weather_aggregator.model.WeatherData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * The Database class acts as a repository for weather data,
 * offering various operations such as insertion, existence check, 
 * data retrieval and deletion. It interacts with the underlying
 * database via JdbcTemplate.
 * 
 * author Thomas Moulton
 */
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
        data.setWindDirection(rs.getDouble("wind_direction"));
        data.setPrecipitationChance(rs.getDouble("precipitation_chance"));

        return data;
    };

    /**
     * Constructs a Database instance with the provided JdbcTemplate.
     *
     * @param jdbcTemplate the JdbcTemplate to interact with the database
     */
    public Database(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Inserts weather data into the database if it does not already exist.
     *
     * @param data the weather data to insert
     */
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

    /**
     * Checks if the provided weather data already exists in the database.
     *
     * @param data the weather data to check
     * @return true if the data exists, false otherwise
     */
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

    /**
     * Retrieves all weather data from the database.
     *
     * @return a list of all weather data
     */
    public List<WeatherData> getAllWeatherData() {
        String sql = "SELECT latitude, longitude, utc_time, temperature, wind_speed, wind_direction, precipitation_chance FROM weather_data";
        return jdbcTemplate.query(sql, weatherDataRowMapper);
    }

    /**
     * Deletes all weather data from the database.
     */
    public void deleteAllWeatherData() {
        String sql = "DELETE FROM weather_data";
        jdbcTemplate.update(sql);
    }
}
