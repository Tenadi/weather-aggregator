package moulton.thomas.weather_aggregator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import moulton.thomas.weather_aggregator.model.WeatherData;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost/weather";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public void insertWeatherData(WeatherData data) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO weather_data (latitude, longitude, utc_time, temperature, wind_speed, wind_direction, precipitation_chance) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, data.getLatitude());
            stmt.setDouble(2, data.getLongitude());
            stmt.setString(3, data.getUtcTime());
            stmt.setDouble(4, data.getTemperature());
            stmt.setDouble(5, data.getWindSpeed());
            stmt.setString(6, data.getWindDirection());
            stmt.setDouble(7, data.getPrecipitationChance());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert weather data into the database", e);
        }
    }
}
