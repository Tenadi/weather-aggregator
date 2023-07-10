#!/bin/bash
# This script will build the Docker containers provided the Docker engine is running

# Build Docker containers
docker-compose build

# Start Docker containers in detached mode
docker-compose up -d
