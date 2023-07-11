package moulton.thomas.weather_aggregator.model;

/**
 * The WeatherData class represents a model of a single record of weather data.
 * It includes details such as latitude, longitude, time in UTC, temperature, wind speed, wind direction, and precipitation chance.
 * 
 * Author Thomas Moulton
 */
public class WeatherData {

    private double latitude;
    private double longitude;
    private String utcTime; // Consider using a java.time type, e.g. ZonedDateTime
    private double temperature;
    private double windSpeed;
    private String windDirection;
    private double precipitationChance;

    /**
     * Override the default toString method to provide a more descriptive representation of the WeatherData object.
     * 
     * @return a string representation of the WeatherData object
     */
    @Override
    public String toString() {
        return "WeatherData{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", utcTime='" + utcTime + '\'' +
                ", temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                ", windDirection='" + windDirection + '\'' +
                ", precipitationChance=" + precipitationChance +
                '}';
    }

    // Getters

    /**
     * @return the latitude of the weather data location
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude of the weather data location
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @return the time the data was recorded, in UTC
     */
    public String getUtcTime() {
        return utcTime;
    }

    /**
     * @return the temperature at the time of data collection
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * @return the wind speed at the time of data collection
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * @return the wind direction at the time of data collection
     */
    public String getWindDirection() {
        return windDirection;
    }

    /**
     * @return the chance of precipitation at the time of data collection
     */
    public double getPrecipitationChance() {
        return precipitationChance;
    }

    // Setters

    /**
     * @param latitude the latitude of the weather data location
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @param longitude the longitude of the weather data location
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @param utcTime the time the data was recorded, in UTC
     */
    public void setUtcTime(String utcTime) {
        this.utcTime = utcTime;
    }

    /**
     * @param temperature the temperature at the time of data collection
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * @param windSpeed the wind speed at the time of data collection
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * @param windDirection the wind direction at the time of data collection
     */
    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * @param precipitationChance the chance of precipitation at the time of data collection
     */
    public void setPrecipitationChance(double precipitationChance) {
        this.precipitationChance = precipitationChance;
    }
}
