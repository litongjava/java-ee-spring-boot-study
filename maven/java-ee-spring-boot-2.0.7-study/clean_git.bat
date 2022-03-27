@echo off
set cdir=%~dp0

for /f "delims=" %%i in ('dir /ad/b/s "%cdir%" ') do ( 
  if "%%~nxi" equ ".git" (
    echo %%~dpi.git
    del %%~dpi.git
  )
)
pause