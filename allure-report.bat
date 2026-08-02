@echo off
setlocal

cd /d "%~dp0"

set "ADB=%LOCALAPPDATA%\Android\Sdk\platform-tools\adb.exe"
set "PACKAGE=ru.iteco.fmhandroid"
set "REMOTE_RESULTS=files/allure-results"
set "RESULTS=allure-results"
set "REPORT=allure-report"
set "ARCHIVE=allure-results.tar"

echo.
echo ===== Allure Android Report =====
echo.
if exist "%RESULTS%" rmdir /s /q "%RESULTS%"
if exist "%REPORT%" rmdir /s /q "%REPORT%"
if not exist "%ADB%" (
    echo ERROR: adb.exe was not found:
    echo %ADB%
    pause
    exit /b 1
)

echo Checking emulator...
"%ADB%" get-state >nul 2>&1

if errorlevel 1 (
    echo ERROR: Emulator is not connected.
    pause
    exit /b 1
)

echo Checking results on emulator...
"%ADB%" shell run-as %PACKAGE% ls "%REMOTE_RESULTS%" >nul 2>&1

if errorlevel 1 (
    echo ERROR: Allure results were not found on emulator.
    echo Expected path: /data/data/%PACKAGE%/%REMOTE_RESULTS%
    echo Run at least one Android test first.
    pause
    exit /b 1
)

if exist "%RESULTS%" rmdir /s /q "%RESULTS%"
if exist "%REPORT%" rmdir /s /q "%REPORT%"
if exist "%ARCHIVE%" del /q "%ARCHIVE%"

echo Copying Allure results from emulator...

"%ADB%" exec-out run-as %PACKAGE% sh -c "cd files && tar -cf - allure-results" > "%ARCHIVE%"

if errorlevel 1 (
    echo ERROR: Failed to copy Allure results.
    pause
    exit /b 1
)

echo Extracting results...

tar -xf "%ARCHIVE%"

if errorlevel 1 (
    echo ERROR: Failed to extract archive.
    pause
    exit /b 1
)

del /q "%ARCHIVE%"

if not exist "%RESULTS%" (
    echo ERROR: Local allure-results folder was not created.
    pause
    exit /b 1
)

echo Generating Allure report...

call allure generate "%RESULTS%" --clean -o "%REPORT%"

if errorlevel 1 (
    echo ERROR: Allure report generation failed.
    pause
    exit /b 1
)

echo Opening Allure report...

call allure open "%REPORT%"

endlocal