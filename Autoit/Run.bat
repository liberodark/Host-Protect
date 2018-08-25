@echo off
pushd "%~dp0"
REM Create Task for AutoUpdate
set directorio=%~dp0")
set name="SecuZer"
SCHTASKS /Create /TN %name% /TR "%directorio%Hosts.exe" /SC DAILY /ST 11:59:59 /RU SYSTEM /RL Highest /F
exit