@echo off
setlocal
set TARGET=..\src\main\resources\public\img
set CONVERT=C:\Progra~1\ImageMagick-6.8.3-Q16\convert.exe
set QUALITY=-quality 85
set SIZE=-geometry 51x51

call st Pfor "*.png" "-c=%CONVERT% %SIZE% %QUALITY%  \"!\!.!\" %TARGET%\!.!"

endlocal
