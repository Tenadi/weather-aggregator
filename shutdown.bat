@echo off
REM This will close the running Docker Conatiners. Note their data volume will persist.

:: Start Docker containers in detached mode
docker-compose down

:: Pause on complete
pause