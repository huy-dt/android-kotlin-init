@echo off
setlocal

REM ===== READ ARGS =====
set BASE_PATH=%1
set OLD_PKG=%2
set NEW_PKG=%3

REM ===== PROMPT IF MISSING =====
if "%OLD_PKG%"=="" (
  set /p "OLD_PKG=Enter OLD_PKG [default com.xxx.app]: "
)

if "%NEW_PKG%"=="" (
  set /p "NEW_PKG=Enter NEW_PKG [default com.yyy.app]: "
)

if "%BASE_PATH%"=="" (
  set /p "BASE_PATH=Enter BASE_PATH [default app]: "
)

REM ===== DEFAULT =====
if "%BASE_PATH%"=="" set BASE_PATH=app
if "%OLD_PKG%"=="" set OLD_PKG=com.xxx.app
if "%NEW_PKG%"=="" set NEW_PKG=com.yyy.app

REM ===== VALIDATE =====
if "%OLD_PKG%"=="" (
  echo ERROR OLD_PKG is empty
  pause
  exit /b 1
)

if "%NEW_PKG%"=="" (
  echo ERROR NEW_PKG is empty
  pause
  exit /b 1
)

REM ===== INFO =====
set LOG_FILE=refactor.log

echo.
echo BASEPATH = %BASE_PATH%
echo OLD_PKG  = %OLD_PKG%
echo NEW_PKG  = %NEW_PKG%
echo.

REM ===== CALL POWERSHELL =====
powershell -NoProfile -ExecutionPolicy Bypass ^
  -File refact_package.ps1 ^
  -OldPkg "%OLD_PKG%" ^
  -NewPkg "%NEW_PKG%" ^
  -BasePath "%BASE_PATH%" ^
  -LogFile "%LOG_FILE%"

echo.
echo DONE. Check %LOG_FILE%
pause
