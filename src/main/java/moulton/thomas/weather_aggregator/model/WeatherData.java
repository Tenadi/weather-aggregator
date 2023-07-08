package moulton.thomas.weather_aggregator.model;

public class WeatherData {
    private double latitude;
    private double longitude;
    private String utcTime; // Consider using a java.time type, e.g. ZonedDateTime
    private double temperature;
    private double windSpeed;
    private String windDirection;
    private double precipitationChance;

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
    
    // getters
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getUtcTime() {
        return utcTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public double getPrecipitationChance() {
        return precipitationChance;
    }

    // setters
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setUtcTime(String utcTime) {
        this.utcTime = utcTime;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public void setPrecipitationChance(double precipitationChance) {
        this.precipitationChance = precipitationChance;
    }
}
