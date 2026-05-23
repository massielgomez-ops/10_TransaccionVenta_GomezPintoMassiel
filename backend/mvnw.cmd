@echo off
setlocal

where mvn >nul 2>nul
if %ERRORLEVEL% EQU 0 (
  mvn %*
  exit /b %ERRORLEVEL%
)

echo mvn was not found on PATH. Please install Maven or use a Maven-aware environment.
exit /b 1
