@echo off
REM This scipt will build the docker containers provided Docker engine is running

:: Build Docker containers
docker-compose build

:: Start Docker containers in detached mode
docker-compose up -d
