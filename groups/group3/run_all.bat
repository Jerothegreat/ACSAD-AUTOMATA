@echo off
color 0A
title GROUP 3 - AUTOMATA Lab Acts Launcher

:menu
cls
echo ========================================
echo   GROUP 3 - AUTOMATA Lab Acts
echo ========================================
echo.
echo Group Members:
echo   - Christian Josh T. Cortez
echo   - Kwin Gabrille Talattad
echo   - Jhon Jellar Z. Miranda
echo   - John Carlo Santiago
echo.
echo ========================================
echo   LAUNCHER MENU
echo ========================================
echo.
echo [1] Run JavaScript (Browser)
echo [2] Run Python (Command Line)
echo [3] Run BOTH JavaScript and Python
echo [4] Exit
echo.
echo ========================================
set /p choice="Enter your choice (1-4): "

if "%choice%"=="1" goto javascript
if "%choice%"=="2" goto python
if "%choice%"=="3" goto both
if "%choice%"=="4" goto exit
echo Invalid choice! Please try again.
timeout /t 2 /nobreak >nul
goto menu

:javascript
cls
echo ========================================
echo   Launching JavaScript Applications...
echo ========================================
echo.
call compile_and_run.bat
goto menu

:python
cls
echo ========================================
echo   Launching Python Applications...
echo ========================================
echo.
call run_python.bat
goto menu

:both
cls
echo ========================================
echo   Launching ALL Applications...
echo ========================================
echo.
echo Starting JavaScript applications...
start "" cmd /c compile_and_run.bat
timeout /t 2 /nobreak >nul
echo.
echo Starting Python applications...
start "" cmd /c run_python.bat
timeout /t 2 /nobreak >nul
echo.
echo All applications launched!
echo.
pause
goto menu

:exit
cls
echo.
echo Thank you for using GROUP 3 AUTOMATA Lab Acts!
echo.
timeout /t 2 /nobreak >nul
exit
