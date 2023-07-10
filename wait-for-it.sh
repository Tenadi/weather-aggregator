#!/usr/bin/env bash
# wait-for-it.sh

set -e

host_port="$1"
shift

# Split the host and port into separate variables
host=$(echo $host_port | cut -d : -f 1)
port=$(echo $host_port | cut -d : -f 2)

until PGPASSWORD=$POSTGRES_PASSWORD psql -h "$host" -p "$port" -U "$POSTGRES_USER" -c '\q'; do
  >&2 echo "Postgres is unavailable - sleeping"
  sleep 1
done

>&2 echo "Postgres is up - executing command"

# Execute the command directly instead of passing it as an argument
java -jar /usr/local/lib/weather-aggregator-0.0.1-SNAPSHOT.jar
