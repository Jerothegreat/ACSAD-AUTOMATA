@echo off
echo ========================================
echo   GROUP 3 - JavaScript Launcher
echo ========================================
echo.
echo Group Members:
echo   - Christian Josh T. Cortez
echo   - Kwin Gabrille Talattad
echo   - Jhon Jellar Z. Miranda
echo   - John Carlo Santiago
echo.
echo Opening all 7 JavaScript applications...
echo.

REM Open all HTML files in default browser
start "" "javascript\division_algorithm.html"
timeout /t 1 /nobreak >nul

start "" "javascript\euclidean_algorithm.html"
timeout /t 1 /nobreak >nul

start "" "javascript\collatz_sequence.html"
timeout /t 1 /nobreak >nul

start "" "javascript\fibonacci_sequence.html"
timeout /t 1 /nobreak >nul

start "" "javascript\lucas_sequence.html"
timeout /t 1 /nobreak >nul

start "" "javascript\tribonacci_sequence.html"
timeout /t 1 /nobreak >nul

start "" "javascript\palindrome_test.html"
timeout /t 1 /nobreak >nul

echo.
echo All applications have been launched!
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
echo Press any key to exit...
pause >nul
