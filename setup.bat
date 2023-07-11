@echo off
REM This scipt will build the docker containers

:: Ensure wsl updated
wsl --update

:: Build Docker containers
docker-compose build

:: Pause on complete
pause