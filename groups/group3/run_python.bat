@echo off
echo ========================================
echo   GROUP 3 - Python Launcher
echo ========================================
echo.
echo Group Members:
echo   - Christian Josh T. Cortez
echo   - Kwin Gabrille Talattad
echo   - Jhon Jellar Z. Miranda
echo   - John Carlo Santiago
echo.
echo Checking Python installation...
python --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Python is not installed or not in PATH!
    echo Please install Python from https://www.python.org/
    echo.
    pause
    exit /b 1
)
echo Python is installed!
echo.
echo Opening all 7 Python applications...
echo Each will run in a separate command window.
echo.

REM Open all Python files in separate command windows
start "Division Algorithm" cmd /k "cd /d "%~dp0" && python py\division_algorithm.py"
timeout /t 1 /nobreak >nul

start "Euclidean Algorithm" cmd /k "cd /d "%~dp0" && python py\euclidean_algorithm.py"
timeout /t 1 /nobreak >nul

start "Collatz Sequence" cmd /k "cd /d "%~dp0" && python py\collatz_sequence.py"
timeout /t 1 /nobreak >nul

start "Fibonacci Sequence" cmd /k "cd /d "%~dp0" && python py\fibonacci_sequence.py"
timeout /t 1 /nobreak >nul

start "Lucas Sequence" cmd /k "cd /d "%~dp0" && python py\lucas_sequence.py"
timeout /t 1 /nobreak >nul

start "Tribonacci Sequence" cmd /k "cd /d "%~dp0" && python py\tribonacci_sequence.py"
timeout /t 1 /nobreak >nul

start "Palindrome Checker" cmd /k "cd /d "%~dp0" && python py\palindrome_test.py"
timeout /t 1 /nobreak >nul

echo.
echo All Python applications have been launched!
echo.
echo Applications opened:
echo   1. Division Algorithm
echo   2. Euclidean Algorithm (GCD/LCM)
echo   3. Collatz Sequence Generator
echo   4. Fibonacci Sequence Generator
echo   5. Lucas Sequence Generator
echo   6. Tribonacci Sequence Generator
echo   7. Palindrome Checker
echo.
echo Each program is running in its own window.
echo Close each window when you're done testing.
echo.
echo Press any key to exit this launcher...
pause >nul
