FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/weather-aggregator-0.0.1-SNAPSHOT.jar /usr/local/lib/weather-aggregator-0.0.1-SNAPSHOT.jar
COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh

# Install PostgreSQL client
RUN apt-get update && apt-get install -y postgresql-client

EXPOSE 5433
ENTRYPOINT ["wait-for-it.sh", "db:5432", "--", "java", "-cp", "/usr/local/lib/weather-aggregator-0.0.1-SNAPSHOT.jar", "org.springframework.boot.loader.JarLauncher"]
