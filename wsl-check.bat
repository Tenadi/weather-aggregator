@echo off

REM Check if WSL is installed by running "wsl --list"
wsl --list >nul 2>nul

IF %ERRORLEVEL% EQU 0 (
    REM WSL is installed
    echo WSL is already installed.
    echo Proceeding with the next steps...
) ELSE (
    REM WSL is not installed
    echo WSL is not currently installed.
    set /p choice="Do you want to install WSL? (Y/N): "
    if /i "%choice%"=="Y" (
        echo Installing WSL...
        wsl --install
    ) else (
        echo Exiting the script.
        exit /b
    )
)

pause
