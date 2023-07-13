CREATE TABLE weather_data (
    latitude DECIMAL NOT NULL,
    longitude DECIMAL NOT NULL,
    utc_time TIMESTAMP NOT NULL,
    temperature DECIMAL NOT NULL,
    wind_speed DECIMAL NOT NULL,
    wind_direction DECIMAL NOT NULL,
    precipitation_chance DECIMAL NOT NULL
);
