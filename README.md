# Weather Aggregator

Weather Aggregator is a Java application that fetches, aggregates, and stores weather data from various sources. The data is stored in a PostgreSQL database and can be retrieved or added to via a RESTful API.

## Prerequisites

Before you begin, ensure you have met the following requirements:

* You have installed the latest version of Java (Java 11 or later)
* You have installed Docker
* You have a basic understanding of Java, Spring Boot and Docker technology

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Installation

1. Clone the repository
git clone https://github.com/your_username_/WeatherAggregator.git

2. Navigate into the project directory
cd WeatherAggregator

3. Build the Docker images for the Java app and the PostgreSQL database:
docker-compose build

4. Run the Docker containers:
docker-compose up

## Usage

* The application automatically fetches and aggregates weather data from predefined sources during startup.
* You can also manually fetch and save weather data from Open-Meteo with a specific latitude and longitude by sending a GET request to `http://localhost:5433/api/fetch-open-metro-today?latitude={lat}&longitude={lon}`.
* You can upload and save weather data in CSV or JSON format by sending a POST request to `http://localhost:5433/api/upload-csv` or `http://localhost:5433/api/upload-json` respectively. The request body should contain the file to be uploaded with key 'file'.
* You can retrieve all stored weather data by sending a GET request to `http://localhost:5433/api/all-weather-data`.

## Deployment

To deploy this on a live system, you will need to configure the application properties (src/main/resources/application.properties) according to your production environment and build the project using:

mvn clean package

This will generate a .jar file in the target directory which can be run with:

java -jar target/weatheraggregator-0.0.1-SNAPSHOT.jar

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [PostgreSQL](https://www.postgresql.org/) - Database
* [Docker](https://www.docker.com/) - Containerization

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
Please adjust the repository URL, username, and other specific details to match your setup.
